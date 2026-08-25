package com.supplyflow;

import com.supplyflow.exception.ProductNotFoundException;
import com.supplyflow.model.OrderSuggestion;
import com.supplyflow.model.Product;
import com.supplyflow.model.StockMovementType;
import com.supplyflow.model.Supplier;

import com.supplyflow.repository.ProductRepository;
import com.supplyflow.repository.SupplierRepository;

import com.supplyflow.service.ProductService;
import com.supplyflow.service.StockMovementService;
import com.supplyflow.service.SupplierService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    private ProductService productService;

    private ProductRepository productRepository;
    private SupplierRepository supplierRepository;
    private SupplierService supplierService;
    private StockMovementService stockMovementService;


    @BeforeEach
    void setUp() {

        productRepository =
                mock(ProductRepository.class);

        supplierRepository =
                mock(SupplierRepository.class);

        supplierService =
                mock(SupplierService.class);

        stockMovementService =
                mock(StockMovementService.class);

        productService =
                new ProductService(
                        productRepository,
                        supplierService,
                        stockMovementService
                );
    }


    // =========================
    // ADD PRODUCT
    // =========================

    @Test
    void shouldAddProduct() {

        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );

        Product product = new Product(
                1L,
                "Laptop",
                45000.0,
                10,
                5,
                supplier
        );

        when(
                productRepository.save(product)
        ).thenReturn(product);

        Product result =
                productService.addProduct(product);

        assertEquals(
                product,
                result
        );

        verify(supplierService)
                .getSupplierById(1L);

        verify(productRepository)
                .save(product);
    }


    // =========================
    // GET PRODUCT
    // =========================

    @Test
    void shouldReturnProductById() {

        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );

        Product product = new Product(
                1L,
                "Laptop",
                45000.0,
                10,
                5,
                supplier
        );

        when(
                productRepository.findById(1L)
        ).thenReturn(
                Optional.of(product)
        );

        Product result =
                productService.getProductById(1L);

        assertEquals(
                "Laptop",
                result.getName()
        );

        assertEquals(
                1L,
                result.getSupplier().getId()
        );
    }


    @Test
    void shouldThrowExceptionWhenProductNotFound() {

        when(
                productRepository.findById(999L)
        ).thenReturn(
                Optional.empty()
        );

        assertThrows(
                ProductNotFoundException.class,
                () -> productService.getProductById(999L)
        );
    }


    // =========================
    // GET ALL PRODUCTS
    // =========================

    @Test
    void shouldReturnAllProducts() {

        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );

        Product product = new Product(
                1L,
                "Laptop",
                45000.0,
                10,
                5,
                supplier
        );

        Product product2 =
                new Product(
                        2L,
                        "Mouse",
                        500.0,
                        20,
                        5,
                        supplier
                );

        when(
                productRepository.findAll()
        ).thenReturn(
                Arrays.asList(
                        product,
                        product2
                )
        );

        assertEquals(
                2,
                productService
                        .getAllProducts()
                        .size()
        );
    }


    // =========================
    // INCREASE STOCK
    // =========================

    @Test
    void shouldIncreaseStock() {

        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );

        Product product = new Product(
                1L,
                "Laptop",
                45000.0,
                10,
                5,
                supplier
        );

        when(
                productRepository.findById(1L)
        ).thenReturn(
                Optional.of(product)
        );

        when(
                productRepository.save(product)
        ).thenReturn(product);

        productService.increaseStock(
                1L,
                5
        );

        assertEquals(
                15,
                product.getStockQuantity()
        );

        verify(productRepository)
                .save(product);

        verify(stockMovementService)
                .createMovement(
                        1L,
                        5,
                        StockMovementType.STOCK_INCREASE
                );
    }


    // =========================
    // DECREASE STOCK
    // =========================

    @Test
    void shouldDecreaseStock() {

        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );

        Product product = new Product(
                1L,
                "Laptop",
                45000.0,
                10,
                5,
                supplier
        );

        when(
                productRepository.findById(1L)
        ).thenReturn(
                Optional.of(product)
        );

        when(
                productRepository.save(product)
        ).thenReturn(product);

        productService.decreaseStock(
                1L,
                3
        );

        assertEquals(
                7,
                product.getStockQuantity()
        );

        verify(productRepository)
                .save(product);

        verify(stockMovementService)
                .createMovement(
                        1L,
                        3,
                        StockMovementType.STOCK_DECREASE
                );
    }


    // =========================
    // CRITICAL PRODUCTS
    // =========================

    @Test
    void shouldReturnCriticalProducts() {
        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );
        Product criticalProduct =
                new Product(
                        1L,
                        "Laptop",
                        25000.0,
                        3,
                        5,
                        supplier
                );

        Product normalProduct =
                new Product(
                        2L,
                        "Mouse",
                        500.0,
                        10,
                        5,
                        supplier
                );

        when(
                productRepository.findAll()
        ).thenReturn(
                Arrays.asList(
                        criticalProduct,
                        normalProduct
                )
        );

        assertEquals(
                1,
                productService
                        .getCriticalStockProducts()
                        .size()
        );
    }


    // =========================
    // ORDER SUGGESTION
    // =========================

    @Test
    void shouldCreateOrderSuggestionForCriticalProduct() {
        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );
        Product product =
                new Product(
                        1L,
                        "Laptop",
                        25000.0,
                        3,
                        5,
                        supplier
                );

        when(
                productRepository.findById(1L)
        ).thenReturn(
                Optional.of(product)
        );

        OrderSuggestion suggestion =
                productService.createOrderSuggestion(1L);

        assertEquals(
                1L,
                suggestion.getProductId()
        );

        assertEquals(
                1L,
                suggestion.getSupplierId()
        );

        assertEquals(
                7,
                suggestion.getRecommendedQuantity()
        );
    }


    @Test
    void shouldThrowExceptionWhenProductIsNotCritical() {
        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );
        Product product =
                new Product(
                        1L,
                        "Laptop",
                        25000.0,
                        10,
                        5,
                        supplier
                );

        when(
                productRepository.findById(1L)
        ).thenReturn(
                Optional.of(product)
        );

        assertThrows(
                IllegalStateException.class,
                () -> productService.createOrderSuggestion(1L)
        );
    }


    // =========================
    // ALL ORDER SUGGESTIONS
    // =========================

    @Test
    void shouldReturnOrderSuggestionsForCriticalProducts() {
        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );
        Product criticalProduct =
                new Product(
                        1L,
                        "Laptop",
                        25000.0,
                        3,
                        5,
                        supplier
                );

        Product normalProduct =
                new Product(
                        2L,
                        "Mouse",
                        500.0,
                        10,
                        5,
                        supplier
                );

        when(
                productRepository.findAll()
        ).thenReturn(
                Arrays.asList(
                        criticalProduct,
                        normalProduct
                )
        );

        assertEquals(
                1,
                productService
                        .getAllOrderSuggestions()
                        .size()
        );
    }
}