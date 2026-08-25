package com.supplyflow.controller;

import com.supplyflow.dto.CreateSupplierRequest;
import com.supplyflow.dto.UpdateSupplierRequest;
import com.supplyflow.model.Supplier;
import com.supplyflow.service.SupplierService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
public class SupplierController {

    private final SupplierService supplierService;


    public SupplierController(
            SupplierService supplierService
    ) {
        this.supplierService = supplierService;
    }


    // =========================
    // GET ALL
    // =========================

    @GetMapping
    public List<Supplier> getAllSuppliers() {

        return supplierService.getAllSuppliers();
    }


    // =========================
    // GET BY ID
    // =========================

    @GetMapping("/{id}")
    public Supplier getSupplierById(
            @PathVariable("id") Long id
    ) {

        return supplierService.getSupplierById(id);
    }


    // =========================
    // CREATE
    // =========================

    @PostMapping
    public ResponseEntity<Supplier> createSupplier(
            @Valid @RequestBody CreateSupplierRequest request
    ) {

        Supplier createdSupplier =
                supplierService.createSupplier(
                        request.getName(),
                        request.getEmail(),
                        request.getPhone()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(createdSupplier);
    }


    // =========================
    // UPDATE
    // =========================

    @PutMapping("/{id}")
    public ResponseEntity<Supplier> updateSupplier(
            @PathVariable("id") Long id,
            @Valid @RequestBody UpdateSupplierRequest request
    ) {

        Supplier updatedSupplier =
                new Supplier(
                        id,
                        request.getName(),
                        request.getEmail(),
                        request.getPhone()
                );

        Supplier result =
                supplierService.updateSupplier(
                        id,
                        updatedSupplier
                );

        return ResponseEntity.ok(result);
    }


    // =========================
    // DELETE
    // =========================

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(
            @PathVariable("id") Long id
    ) {

        supplierService.deleteSupplier(id);

        return ResponseEntity.noContent().build();
    }
}