package com.supplyflow;

import com.supplyflow.controller.SupplierController;
import com.supplyflow.exception.GlobalExceptionHandler;
import com.supplyflow.model.Supplier;
import com.supplyflow.service.SupplierService;
import static org.mockito.Mockito.verify;
import org.junit.jupiter.api.Test;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(SupplierController.class)
@Import(GlobalExceptionHandler.class)
public class SupplierControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private SupplierService supplierService;


    // =========================
    // GET ALL SUPPLIERS
    // =========================

    @Test
    void shouldReturnAllSuppliers()
            throws Exception {

        Supplier supplier1 =
                new Supplier(
                        1L,
                        "Tech Supplier",
                        "info@techsupplier.com",
                        "555-111-2233"
                );

        Supplier supplier2 =
                new Supplier(
                        2L,
                        "Global Electronics",
                        "contact@globalelectronics.com",
                        "555-444-5566"
                );


        when(
                supplierService.getAllSuppliers()
        ).thenReturn(
                List.of(
                        supplier1,
                        supplier2
                )
        );


        mockMvc.perform(
                        get("/suppliers")
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
                                .value("Tech Supplier")
                )
                .andExpect(
                        jsonPath("$[1].id")
                                .value(2)
                )
                .andExpect(
                        jsonPath("$[1].name")
                                .value("Global Electronics")
                );
    }


    // =========================
    // GET SUPPLIER BY ID
    // =========================

    @Test
    void shouldReturnSupplierById()
            throws Exception {

        Supplier supplier =
                new Supplier(
                        1L,
                        "Tech Supplier",
                        "info@techsupplier.com",
                        "555-111-2233"
                );


        when(
                supplierService.getSupplierById(1L)
        ).thenReturn(supplier);


        mockMvc.perform(
                        get("/suppliers/1")
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
                                .value("Tech Supplier")
                )
                .andExpect(
                        jsonPath("$.email")
                                .value(
                                        "info@techsupplier.com"
                                )
                )
                .andExpect(
                        jsonPath("$.phone")
                                .value(
                                        "555-111-2233"
                                )
                );
    }


    // =========================
    // CREATE SUPPLIER
    // =========================

    @Test
    void shouldCreateSupplier()
            throws Exception {

        Supplier supplier =
                new Supplier(
                        1L,
                        "Tech Supplier",
                        "info@techsupplier.com",
                        "555-111-2233"
                );


        when(
                supplierService.createSupplier(
                        eq("Tech Supplier"),
                        eq("info@techsupplier.com"),
                        eq("555-111-2233")
                )
        ).thenReturn(supplier);


        String requestBody = "{"
        + "\"name\":\"Tech Supplier\","
        + "\"email\":\"info@techsupplier.com\","
        + "\"phone\":\"555-111-2233\""
        + "}";


        mockMvc.perform(
                        post("/suppliers")
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
                                .value("Tech Supplier")
                )
                .andExpect(
                        jsonPath("$.email")
                                .value(
                                        "info@techsupplier.com"
                                )
                );
    }


    // =========================
    // UPDATE SUPPLIER
    // =========================

    @Test
    void shouldUpdateSupplier()
            throws Exception {

        Supplier updatedSupplier =
                new Supplier(
                        1L,
                        "Updated Supplier",
                        "updated@test.com",
                        "555-999-8888"
                );


        when(
                supplierService.updateSupplier(
                        eq(1L),
                        any(Supplier.class)
                )
        ).thenReturn(updatedSupplier);


        String requestBody = "{"
                + "\"name\":\"Updated Supplier\","
                + "\"email\":\"updated@test.com\","
                + "\"phone\":\"555-999-8888\""
                + "}";


        mockMvc.perform(
                        put("/suppliers/1")
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
                                .value(
                                        "Updated Supplier"
                                )
                )
                .andExpect(
                        jsonPath("$.email")
                                .value(
                                        "updated@test.com"
                                )
                );
    }


    // =========================
    // DELETE SUPPLIER
    // =========================

    @Test
        void shouldDeleteSupplier()
                throws Exception {

        mockMvc.perform(
                        delete("/suppliers/1")
                )
                .andExpect(
                        status().isNoContent()
                );

        verify(
                supplierService
        ).deleteSupplier(1L);
        }
}