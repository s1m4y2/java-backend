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
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long productId;

    private Long supplierId;

    private int quantity;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private LocalDateTime createdAt;


    public Order() {
    }


    public Order(
            Long productId,
            Long supplierId,
            int quantity
    ) {

        this.productId = productId;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.status = OrderStatus.CREATED;
        this.createdAt = LocalDateTime.now();
    }


    public Long getId() {
        return id;
    }


    public Long getProductId() {
        return productId;
    }


    public Long getSupplierId() {
        return supplierId;
    }


    public int getQuantity() {
        return quantity;
    }


    public OrderStatus getStatus() {
        return status;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void approve() {

        if (status != OrderStatus.CREATED) {

            throw new IllegalStateException(
                    "Only CREATED orders can be approved."
            );
        }

        status = OrderStatus.APPROVED;
    }


    public void deliver() {

        if (status != OrderStatus.APPROVED) {

            throw new IllegalStateException(
                    "Only APPROVED orders can be delivered."
            );
        }

        status = OrderStatus.DELIVERED;
    }


    public void cancel() {

        if (status != OrderStatus.CREATED) {

            throw new IllegalStateException(
                    "Only CREATED orders can be cancelled."
            );
        }

        status = OrderStatus.CANCELLED;
    }


    @Override
    public String toString() {

        return "Order{" +
                "id=" + id +
                ", productId=" + productId +
                ", supplierId=" + supplierId +
                ", quantity=" + quantity +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }
}