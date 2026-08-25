package com.supplyflow.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

public class UpdateProductRequest {

    @NotBlank(
            message = "Product name is required."
    )
    private String name;


    @NotNull(
            message = "Price is required."
    )
    @Positive(
            message = "Price must be greater than 0."
    )
    private Double price;


    @NotNull(
            message = "Stock quantity is required."
    )
    @PositiveOrZero(
            message = "Stock quantity cannot be negative."
    )
    private Integer stockQuantity;


    @NotNull(
            message = "Minimum stock level is required."
    )
    @PositiveOrZero(
            message = "Minimum stock level cannot be negative."
    )
    private Integer minimumStockLevel;


    @NotNull(
            message = "Supplier ID is required."
    )
    private Long supplierId;


    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }


    public Double getPrice() {
        return price;
    }


    public void setPrice(
            Double price
    ) {
        this.price = price;
    }


    public Integer getStockQuantity() {
        return stockQuantity;
    }


    public void setStockQuantity(
            Integer stockQuantity
    ) {
        this.stockQuantity = stockQuantity;
    }


    public Integer getMinimumStockLevel() {
        return minimumStockLevel;
    }


    public void setMinimumStockLevel(
            Integer minimumStockLevel
    ) {
        this.minimumStockLevel =
                minimumStockLevel;
    }


    public Long getSupplierId() {
        return supplierId;
    }


    public void setSupplierId(
            Long supplierId
    ) {
        this.supplierId = supplierId;
    }
}