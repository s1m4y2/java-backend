package com.supplyflow.dto;

import com.supplyflow.model.StockMovement;
import com.supplyflow.model.StockMovementType;

import java.time.LocalDateTime;

public class StockMovementResponse {

    private final Long id;

    private final Long productId;

    private final int quantity;

    private final StockMovementType type;

    private final LocalDateTime createdAt;


    public StockMovementResponse(
            Long id,
            Long productId,
            int quantity,
            StockMovementType type,
            LocalDateTime createdAt
    ) {

        this.id = id;
        this.productId = productId;
        this.quantity = quantity;
        this.type = type;
        this.createdAt = createdAt;
    }


    public static StockMovementResponse from(
            StockMovement stockMovement
    ) {

        return new StockMovementResponse(
                stockMovement.getId(),
                stockMovement.getProductId(),
                stockMovement.getQuantity(),
                stockMovement.getType(),
                stockMovement.getCreatedAt()
        );
    }


    public Long getId() {
        return id;
    }


    public Long getProductId() {
        return productId;
    }


    public int getQuantity() {
        return quantity;
    }


    public StockMovementType getType() {
        return type;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}