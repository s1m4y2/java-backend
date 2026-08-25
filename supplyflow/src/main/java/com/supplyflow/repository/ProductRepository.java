package com.supplyflow.repository;

import com.supplyflow.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository
        extends JpaRepository<Product, Long> {

    boolean existsBySupplier_Id(Long supplierId);
}