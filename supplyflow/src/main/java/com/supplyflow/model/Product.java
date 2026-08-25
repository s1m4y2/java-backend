package com.supplyflow.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Double price;

    private Integer stockQuantity;

    private Integer minimumStockLevel;


    // =========================
    // SUPPLIER RELATION
    // =========================

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Supplier supplier;


    public Product() {
    }


    public Product(
            Long id,
            String name,
            Double price,
            Integer stockQuantity,
            Integer minimumStockLevel,
            Supplier supplier
    ) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.minimumStockLevel = minimumStockLevel;
        this.supplier = supplier;
    }


    // =========================
    // GETTERS
    // =========================

    public Long getId() {
        return id;
    }


    public String getName() {
        return name;
    }


    public Double getPrice() {
        return price;
    }


    public Integer getStockQuantity() {
        return stockQuantity;
    }


    public Integer getMinimumStockLevel() {
        return minimumStockLevel;
    }


    public Supplier getSupplier() {
        return supplier;
    }


    // =========================
    // SETTERS
    // =========================

    public void setName(String name) {
        this.name = name;
    }


    public void setPrice(Double price) {
        this.price = price;
    }


    public void setStockQuantity(
            Integer stockQuantity
    ) {
        this.stockQuantity = stockQuantity;
    }


    public void setMinimumStockLevel(
            Integer minimumStockLevel
    ) {
        this.minimumStockLevel = minimumStockLevel;
    }


    public void setSupplier(
            Supplier supplier
    ) {
        this.supplier = supplier;
    }


    // =========================
    // STOCK OPERATIONS
    // =========================

    public void increaseStock(
            int quantity
    ) {

        this.stockQuantity += quantity;
    }


    public void decreaseStock(
            int quantity
    ) {

        if (quantity > this.stockQuantity) {

            throw new IllegalArgumentException(
                    "Insufficient stock."
            );
        }

        this.stockQuantity -= quantity;
    }


    // =========================
    // STOCK STATUS
    // =========================

    public boolean isStockCritical() {

        return stockQuantity <= minimumStockLevel;
    }
}