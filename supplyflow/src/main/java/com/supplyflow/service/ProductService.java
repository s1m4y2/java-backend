package com.supplyflow.service;

import com.supplyflow.exception.ProductNotFoundException;

import com.supplyflow.model.OrderSuggestion;
import com.supplyflow.model.Product;
import com.supplyflow.model.StockMovementType;
import com.supplyflow.model.Supplier;

import com.supplyflow.repository.ProductRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    private final SupplierService supplierService;

    private final StockMovementService stockMovementService;


    public ProductService(
            ProductRepository productRepository,
            SupplierService supplierService,
            StockMovementService stockMovementService
    ) {

        this.productRepository =
                productRepository;

        this.supplierService =
                supplierService;

        this.stockMovementService =
                stockMovementService;
    }


    // =========================
    // CALCULATE RECOMMENDED QUANTITY
    // =========================

    private int calculateRecommendedQuantity(
                Product product
        ) {

        return Math.max(
                0,
                product.getMinimumStockLevel() * 2
                        - product.getStockQuantity()
        );
        }


    // =========================
    // ADD PRODUCT
    // =========================

    public Product addProduct(
            Product product
    ) {

        Supplier supplier =
                product.getSupplier();

        if (supplier == null) {

            throw new IllegalArgumentException(
                    "Supplier is required."
            );
        }

        supplierService.getSupplierById(
                supplier.getId()
        );

        return productRepository.save(
                product
        );
    }


    // =========================
    // CREATE PRODUCT
    // =========================

    public Product createProduct(
            String name,
            double price,
            int stockQuantity,
            int minimumStockLevel,
            Long supplierId
    ) {

        Supplier supplier =
                supplierService.getSupplierById(
                        supplierId
                );

        Product product =
                new Product(
                        null,
                        name,
                        price,
                        stockQuantity,
                        minimumStockLevel,
                        supplier
                );

        return productRepository.save(
                product
        );
    }


    // =========================
    // GET PRODUCT BY ID
    // =========================

    public Product getProductById(
            Long id
    ) {

        return productRepository
                .findById(id)
                .orElseThrow(
                        () ->
                                new ProductNotFoundException(
                                        id
                                )
                );
    }


    // =========================
    // GET ALL PRODUCTS
    // =========================

    public List<Product>
    getAllProducts() {

        return productRepository.findAll();
    }


    // =========================
    // GET CRITICAL PRODUCTS
    // =========================

    public List<Product>
    getCriticalStockProducts() {

        return productRepository
                .findAll()
                .stream()
                .filter(
                        Product::isStockCritical
                )
                .toList();
    }


    // =========================
    // INCREASE STOCK
    // =========================

    public void increaseStock(
            Long productId,
            int quantity
    ) {

        increaseStock(
                productId,
                quantity,
                StockMovementType.STOCK_INCREASE
        );
    }


    public void increaseStock(
            Long productId,
            int quantity,
            StockMovementType movementType
    ) {

        Product product =
                getProductById(productId);

        product.increaseStock(
                quantity
        );

        productRepository.save(
                product
        );

        stockMovementService.createMovement(
                productId,
                quantity,
                movementType
        );
    }


    // =========================
    // DECREASE STOCK
    // =========================

    public void decreaseStock(
            Long productId,
            int quantity
    ) {

        Product product =
                getProductById(productId);

        product.decreaseStock(
                quantity
        );

        productRepository.save(
                product
        );

        stockMovementService.createMovement(
                productId,
                quantity,
                StockMovementType.STOCK_DECREASE
        );
    }


    // =========================
    // CREATE ORDER SUGGESTION
    // =========================

    public OrderSuggestion createOrderSuggestion(
            Long productId
    ) {

        Product product =
                getProductById(productId);

        if (!product.isStockCritical()) {

            throw new IllegalStateException(
                    "Order suggestion can only be created for critical stock products."
            );
        }

        int recommendedQuantity =
                calculateRecommendedQuantity(
                        product
                );

        return new OrderSuggestion(
                product.getId(),
                product.getSupplier().getId(),
                recommendedQuantity
        );
    }


    // =========================
    // GET ALL ORDER SUGGESTIONS
    // =========================

    public List<OrderSuggestion>
    getAllOrderSuggestions() {

        return productRepository
                .findAll()
                .stream()
                .filter(
                        Product::isStockCritical
                )
                .map(product -> {

                    int recommendedQuantity =
                            calculateRecommendedQuantity(
                                    product
                            );

                    return new OrderSuggestion(
                            product.getId(),
                            product.getSupplier().getId(),
                            recommendedQuantity
                    );
                })
                .toList();
    }


    // =========================
    // UPDATE PRODUCT
    // =========================

    public Product updateProduct(
            Long id,
            Product updatedProduct
    ) {

        Product existingProduct =
                getProductById(id);

        Supplier supplier =
                supplierService.getSupplierById(
                        updatedProduct
                                .getSupplier()
                                .getId()
                );

        existingProduct.setName(
                updatedProduct.getName()
        );

        existingProduct.setPrice(
                updatedProduct.getPrice()
        );

        existingProduct.setStockQuantity(
                updatedProduct.getStockQuantity()
        );

        existingProduct.setMinimumStockLevel(
                updatedProduct
                        .getMinimumStockLevel()
        );

        existingProduct.setSupplier(
                supplier
        );

        return productRepository.save(
                existingProduct
        );
    }

    // =========================
    // DELETE PRODUCT
    // =========================

    public void deleteProduct(
            Long id
    ) {

        Product product =
                getProductById(id);

        productRepository.delete(
                product
        );
    }
}