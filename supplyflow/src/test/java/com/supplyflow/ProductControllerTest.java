package com.supplyflow;

import com.supplyflow.controller.ProductController;
import com.supplyflow.exception.GlobalExceptionHandler;
import com.supplyflow.model.Product;
import com.supplyflow.service.ProductService;

import org.junit.jupiter.api.Test;
import com.supplyflow.model.Supplier;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.context.annotation.Import;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProductController.class)
@Import(GlobalExceptionHandler.class)
public class ProductControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private ProductService productService;


    // =========================
    // GET ALL PRODUCTS
    // =========================

    @Test
    void shouldReturnAllProducts()
            throws Exception {

        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );

        Product product1 = new Product(
                1L,
                "Laptop",
                45000.0,
                10,
                5,
                supplier
        );

        Product product2 = new Product(
                2L,
                "Keyboard",
                2500.0,
                20,
                5,
                supplier
        );

        when(
                productService.getAllProducts()
        ).thenReturn(
                List.of(
                        product1,
                        product2
                )
        );


        mockMvc.perform(
                        get("/products")
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$.length()")
                                .value(2)
                )
                .andExpect(
                        jsonPath("$[0].id")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$[0].name")
                                .value("Laptop")
                )
                .andExpect(
                        jsonPath("$[1].id")
                                .value(2)
                )
                .andExpect(
                        jsonPath("$[1].name")
                                .value("Keyboard")
                );
    }


    // =========================
    // GET PRODUCT BY ID
    // =========================

    @Test
    void shouldReturnProductById()
            throws Exception {

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
                productService.getProductById(1L)
        ).thenReturn(product);


        mockMvc.perform(
                        get("/products/1")
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$.id")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.name")
                                .value("Laptop")
                )
                .andExpect(
                        jsonPath("$.price")
                                .value(45000.0)
                )
                .andExpect(
                        jsonPath("$.stockQuantity")
                                .value(10)
                );
    }


    // =========================
    // GET CRITICAL PRODUCTS
    // =========================

    @Test
    void shouldReturnCriticalProducts()
            throws Exception {

        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );

        Product criticalProduct =
                new Product(
                        3L,
                        "Mouse",
                        1000.0,
                        3,
                        8,
                        supplier
                );

        when(
                productService.getCriticalStockProducts()
        ).thenReturn(
                List.of(criticalProduct)
        );


        mockMvc.perform(
                        get("/products/critical")
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$.length()")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$[0].id")
                                .value(3)
                )
                .andExpect(
                        jsonPath("$[0].name")
                                .value("Mouse")
                )
                .andExpect(
                        jsonPath("$[0].stockCritical")
                                .value(true)
                );
    }


    // =========================
    // CREATE PRODUCT
    // =========================

    @Test
    void shouldCreateProduct()
            throws Exception {

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
                productService.createProduct(
                        eq("Laptop"),
                        eq(45000.0),
                        eq(10),
                        eq(5),
                        eq(1L)
                )
        ).thenReturn(product);


        String requestBody = "{"
                + "\"name\":\"Laptop\","
                + "\"price\":45000.0,"
                + "\"stockQuantity\":10,"
                + "\"minimumStockLevel\":5,"
                + "\"supplierId\":1"
                + "}";


        mockMvc.perform(
                        post("/products")
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )
                                .content(requestBody)
                )
                .andExpect(
                        status().isCreated()
                )
                .andExpect(
                        jsonPath("$.id")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.name")
                                .value("Laptop")
                )
                .andExpect(
                        jsonPath("$.price")
                                .value(45000.0)
                );
    }


    // =========================
    // UPDATE PRODUCT
    // =========================

    @Test
    void shouldUpdateProduct()
            throws Exception {
        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );
        Product updatedProduct =
        new Product(
                1L,
                "Updated Laptop",
                50000.0,
                15,
                5,
                supplier
        );

        when(
                productService.updateProduct(
                        eq(1L),
                        any(Product.class)
                )
        ).thenReturn(updatedProduct);


        String requestBody = "{"
                + "\"name\":\"Updated Laptop\","
                + "\"price\":50000.0,"
                + "\"stockQuantity\":15,"
                + "\"minimumStockLevel\":5,"
                + "\"supplierId\":1"
                + "}";


        mockMvc.perform(
                        put("/products/1")
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )
                                .content(requestBody)
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$.id")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.name")
                                .value("Updated Laptop")
                )
                .andExpect(
                        jsonPath("$.price")
                                .value(50000.0)
                );
    }


    // =========================
    // DELETE PRODUCT
    // =========================

    @Test
    void shouldDeleteProduct()
            throws Exception {

        mockMvc.perform(
                        delete("/products/1")
                )
                .andExpect(
                        status().isNoContent()
                );
    }


    // =========================
    // VALIDATION ERROR
    // =========================

    @Test
    void shouldReturnBadRequestWhenProductNameIsInvalid()
            throws Exception {

        String requestBody = "{"
                + "\"name\":\"\","
                + "\"price\":45000.0,"
                + "\"stockQuantity\":10,"
                + "\"minimumStockLevel\":5,"
                + "\"supplierId\":1"
                + "}";


        mockMvc.perform(
                        post("/products")
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )
                                .content(requestBody)
                )
                .andExpect(
                        status().isBadRequest()
                )
                .andExpect(
                        jsonPath("$.error")
                                .value("Validation Failed")
                )
                .andExpect(
                        jsonPath("$.errors.name")
                                .exists()
                );
    }
}