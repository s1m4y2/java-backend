package com.supplyflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class CreateProductRequest {

    @NotBlank(message = "Product name is required.")
    private String name;

    @Positive(message = "Price must be greater than zero.")
    private double price;

    @PositiveOrZero(
            message = "Stock quantity cannot be negative."
    )
    private int stockQuantity;

    @PositiveOrZero(
            message = "Minimum stock level cannot be negative."
    )
    private int minimumStockLevel;

    @NotNull(message = "Supplier ID is required.")
    private Long supplierId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public int getMinimumStockLevel() {
        return minimumStockLevel;
    }

    public void setMinimumStockLevel(
            int minimumStockLevel
    ) {
        this.minimumStockLevel = minimumStockLevel;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }
}