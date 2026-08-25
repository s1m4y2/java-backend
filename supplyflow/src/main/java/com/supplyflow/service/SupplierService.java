package com.supplyflow.service;

import com.supplyflow.exception.SupplierNotFoundException;
import com.supplyflow.model.Supplier;
import com.supplyflow.repository.ProductRepository;
import com.supplyflow.repository.SupplierRepository;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;
    private final ProductRepository productRepository;

    public SupplierService(
            SupplierRepository supplierRepository,
            ProductRepository productRepository
    ) {

        this.supplierRepository =
                supplierRepository;

        this.productRepository =
                productRepository;
    }


    // =========================
    // CREATE
    // =========================

    public Supplier addSupplier(
            Supplier supplier
    ) {

        return supplierRepository.save(
                supplier
        );
    }


    // =========================
    // CREATE SUPPLIER
    // =========================

    public Supplier createSupplier(
            String name,
            String email,
            String phone
    ) {

        Supplier supplier =
                new Supplier(
                        null,
                        name,
                        email,
                        phone
                );

        return supplierRepository.save(
                supplier
        );
    }


    // =========================
    // GET BY ID
    // =========================

    public Supplier getSupplierById(Long id) {

        return supplierRepository
                .findById(id)
                .orElseThrow(
                        () -> new SupplierNotFoundException(id)
                );
        }


    // =========================
    // GET ALL
    // =========================

    public List<Supplier>
    getAllSuppliers() {

        return supplierRepository
                .findAll();
    }


    // =========================
    // UPDATE
    // =========================

    public Supplier updateSupplier(
            Long id,
            Supplier updatedSupplier
    ) {

        Supplier existingSupplier =
                getSupplierById(id);

        existingSupplier.setName(
                updatedSupplier.getName()
        );

        existingSupplier.setEmail(
                updatedSupplier.getEmail()
        );

        existingSupplier.setPhone(
                updatedSupplier.getPhone()
        );

        return supplierRepository.save(
                existingSupplier
        );
    }


    // =========================
    // DELETE
    // =========================

    public void deleteSupplier(
            Long id
    ) {

        Supplier supplier =
                getSupplierById(id);

        boolean hasProducts =
                productRepository.existsBySupplier_Id(id);

        if (hasProducts) {

            throw new IllegalStateException(
                    "Supplier cannot be deleted because it has associated products."
            );
        }

        supplierRepository.delete(
                supplier
        );
    }
}