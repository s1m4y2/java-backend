package com.supplyflow.config;

import com.supplyflow.model.Product;
import com.supplyflow.model.Supplier;
import com.supplyflow.repository.ProductRepository;
import com.supplyflow.repository.SupplierRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initData(
            SupplierRepository supplierRepository,
            ProductRepository productRepository
    ) {

        return args -> {

            if (supplierRepository.count() == 0) {

                // =========================
                // SUPPLIERS
                // =========================

                Supplier supplier1 =
                        new Supplier(
                                null,
                                "Tech Supplier",
                                "info@techsupplier.com",
                                "555-111-2233"
                        );

                Supplier supplier2 =
                        new Supplier(
                                null,
                                "Global Electronics",
                                "contact@globalelectronics.com",
                                "555-444-5566"
                        );


                Supplier savedSupplier1 =
                        supplierRepository.save(
                                supplier1
                        );

                Supplier savedSupplier2 =
                        supplierRepository.save(
                                supplier2
                        );


                // =========================
                // PRODUCTS
                // =========================

                Product product1 =
                        new Product(
                                null,
                                "Laptop",
                                45000.0,
                                10,
                                5,
                                savedSupplier1
                        );

                Product product2 =
                        new Product(
                                null,
                                "Keyboard",
                                2500.0,
                                20,
                                6,
                                savedSupplier2
                        );

                Product product3 =
                        new Product(
                                null,
                                "Mouse",
                                1000.0,
                                3,
                                8,
                                savedSupplier1
                        );


                productRepository.save(product1);

                productRepository.save(product2);

                productRepository.save(product3);
            }
        };
    }
}