package com.supplyflow.controller;

import com.supplyflow.dto.CreateProductRequest;
import com.supplyflow.dto.StockUpdateRequest;
import com.supplyflow.dto.UpdateProductRequest;
import com.supplyflow.dto.ProductResponse;
import com.supplyflow.model.OrderSuggestion;
import com.supplyflow.model.Product;
import com.supplyflow.model.Supplier;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.supplyflow.service.ProductService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/products")
@Tag(
        name = "Products",
        description = "Product management and stock operations"
)
public class ProductController {

    private final ProductService productService;


    public ProductController(
            ProductService productService
    ) {

        this.productService =
                productService;
    }


    // =========================
    // GET ALL PRODUCTS
    // =========================

    @GetMapping
        public List<ProductResponse> getAllProducts() {

        return productService
                .getAllProducts()
                .stream()
                .map(ProductResponse::from)
                .toList();
        }


    // =========================
    // GET CRITICAL PRODUCTS
    // =========================

    @GetMapping("/critical")
        public List<ProductResponse>
        getCriticalStockProducts() {

        return productService
                .getCriticalStockProducts()
                .stream()
                .map(ProductResponse::from)
                .toList();
        }


    // =========================
    // GET ALL ORDER SUGGESTIONS
    // =========================

    @GetMapping("/order-suggestions")
    public List<OrderSuggestion>
    getAllOrderSuggestions() {

        return productService
                .getAllOrderSuggestions();
    }


    // =========================
    // GET ORDER SUGGESTION
    // =========================

    @GetMapping("/{id}/order-suggestion")
    public OrderSuggestion
    getOrderSuggestion(
            @PathVariable("id") Long id
    ) {

        return productService
                .createOrderSuggestion(id);
    }


    // =========================
    // GET PRODUCT BY ID
    // =========================

    @GetMapping("/{id}")
        public ProductResponse getProductById(
                @PathVariable("id") Long id
        ) {

        Product product =
                productService.getProductById(id);

        return ProductResponse.from(product);
        }


    // =========================
    // CREATE PRODUCT
    // =========================

    @PostMapping
        public ResponseEntity<ProductResponse>
        createProduct(
                @Valid
                @RequestBody
                CreateProductRequest request
        ) {

        Product product =
                productService.createProduct(
                        request.getName(),
                        request.getPrice(),
                        request.getStockQuantity(),
                        request.getMinimumStockLevel(),
                        request.getSupplierId()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ProductResponse.from(product));
        }


    // =========================
    // UPDATE PRODUCT
    // =========================

    @PutMapping("/{id}")
        public ResponseEntity<ProductResponse>
        updateProduct(
                @PathVariable("id") Long id,

                @Valid
                @RequestBody
                UpdateProductRequest request
        ) {

        Supplier supplier =
                new Supplier(
                        request.getSupplierId(),
                        null,
                        null,
                        null
                );

        Product product =
                new Product(
                        null,
                        request.getName(),
                        request.getPrice(),
                        request.getStockQuantity(),
                        request.getMinimumStockLevel(),
                        supplier
                );

        Product updatedProduct =
                productService.updateProduct(
                        id,
                        product
                );

        return ResponseEntity
                .ok(
                        ProductResponse.from(updatedProduct)
                );
        }


    // =========================
    // INCREASE STOCK
    // =========================

    @PatchMapping("/{id}/increase-stock")
        public ResponseEntity<ProductResponse>
        increaseStock(

                @PathVariable("id") Long id,

                @Valid
                @RequestBody
                StockUpdateRequest request
        ) {

        productService.increaseStock(
                id,
                request.getQuantity()
        );

        Product product =
                productService.getProductById(id);

        return ResponseEntity
                .ok(
                        ProductResponse.from(product)
                );
        }


    // =========================
    // DECREASE STOCK
    // =========================

    @PatchMapping("/{id}/decrease-stock")
        public ResponseEntity<ProductResponse>
        decreaseStock(

                @PathVariable("id") Long id,

                @Valid
                @RequestBody
                StockUpdateRequest request
        ) {

        productService.decreaseStock(
                id,
                request.getQuantity()
        );

        Product product =
                productService.getProductById(id);

        return ResponseEntity
                .ok(
                        ProductResponse.from(product)
                );
        }
     // =========================
    // DELETE PRODUCT
    // =========================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable("id") Long id
    ) {

        productService.deleteProduct(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}