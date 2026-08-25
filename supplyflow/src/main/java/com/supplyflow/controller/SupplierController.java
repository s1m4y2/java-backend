package com.supplyflow.controller;

import com.supplyflow.dto.CreateSupplierRequest;
import com.supplyflow.dto.UpdateSupplierRequest;
import com.supplyflow.model.Supplier;
import com.supplyflow.service.SupplierService;
import com.supplyflow.dto.SupplierResponse;
import jakarta.validation.Valid;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suppliers")
@Tag(
        name = "Suppliers",
        description = "Supplier management operations"
)
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
        public List<SupplierResponse> getAllSuppliers() {

        return supplierService
                .getAllSuppliers()
                .stream()
                .map(SupplierResponse::from)
                .toList();
        }


    // =========================
    // GET BY ID
    // =========================

    @GetMapping("/{id}")
        public SupplierResponse getSupplierById(
                @PathVariable("id") Long id
        ) {

        Supplier supplier =
                supplierService.getSupplierById(id);

        return SupplierResponse.from(supplier);
        }


    // =========================
    // CREATE
    // =========================

    @PostMapping
        public ResponseEntity<SupplierResponse> createSupplier(
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
                .body(
                        SupplierResponse.from(createdSupplier)
                );
        }


    // =========================
    // UPDATE
    // =========================

    @PutMapping("/{id}")
        public ResponseEntity<SupplierResponse> updateSupplier(
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

        return ResponseEntity.ok(
                SupplierResponse.from(result)
        );
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