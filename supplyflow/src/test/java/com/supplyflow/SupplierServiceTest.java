package com.supplyflow;

import com.supplyflow.exception.SupplierNotFoundException;
import com.supplyflow.model.Supplier;
import com.supplyflow.repository.ProductRepository;
import com.supplyflow.repository.SupplierRepository;
import com.supplyflow.service.SupplierService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class SupplierServiceTest {

    private SupplierService supplierService;

    private SupplierRepository supplierRepository;
    private ProductRepository productRepository;

    @BeforeEach
    void setUp() {

        supplierRepository = mock(
                SupplierRepository.class
        );

        productRepository = mock(
                ProductRepository.class
        );

        supplierService =
                new SupplierService(
                        supplierRepository,
                        productRepository
                );
    }

    @Test
    void shouldAddSupplier() {

        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );

        supplierService.addSupplier(supplier);

        verify(supplierRepository)
                .save(supplier);
    }

    @Test
    void shouldThrowExceptionWhenSupplierNotFound() {

        when(
                supplierRepository.findById(999L)
        ).thenReturn(Optional.empty());

        assertThrows(
                SupplierNotFoundException.class,
                () -> supplierService.getSupplierById(999L)
        );
    }

    @Test
    void shouldReturnAllSuppliers() {

        Supplier supplier1 = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );

        Supplier supplier2 = new Supplier(
                2L,
                "Global Electronics",
                "contact@globalelectronics.com",
                "555-444-5566"
        );

        when(
                supplierRepository.findAll()
        ).thenReturn(
                Arrays.asList(
                        supplier1,
                        supplier2
                )
        );

        assertEquals(
                2,
                supplierService
                        .getAllSuppliers()
                        .size()
        );
    }

    @Test
        void shouldNotDeleteSupplierWhenItHasProducts() {

        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );

        when(
                supplierRepository.findById(1L)
        ).thenReturn(
                Optional.of(supplier)
        );

        when(
                productRepository.existsBySupplier_Id(1L)
        ).thenReturn(true);

        assertThrows(
                IllegalStateException.class,
                () -> supplierService.deleteSupplier(1L)
        );

        verify(
                supplierRepository,
                never()
        ).delete(supplier);
        }
}