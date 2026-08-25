package com.supplyflow.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock_movements")
public class StockMovement {

    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    private Long productId;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private StockMovementType type;

    private LocalDateTime createdAt;

    protected StockMovement() {
    }

    public StockMovement(
            Long productId,
            int quantity,
            StockMovementType type
    ) {

        this.productId =
                productId;

        this.quantity =
                quantity;

        this.type =
                type;

        this.createdAt =
                LocalDateTime.now();
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

    @Override
    public String toString() {

        return "StockMovement{" +
                "id=" + id +
                ", productId=" + productId +
                ", quantity=" + quantity +
                ", type=" + type +
                ", createdAt=" + createdAt +
                '}';
    }
}