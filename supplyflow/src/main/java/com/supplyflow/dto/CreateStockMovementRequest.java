package com.supplyflow.dto;

import com.supplyflow.model.StockMovementType;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class CreateStockMovementRequest {

    @NotNull
    private Long productId;

    @Positive
    private int quantity;

    @NotNull
    private StockMovementType type;


    public Long getProductId() {
        return productId;
    }


    public int getQuantity() {
        return quantity;
    }


    public StockMovementType getType() {
        return type;
    }
}