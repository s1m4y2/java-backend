package com.supplyflow.dto;

import com.supplyflow.model.Order;
import com.supplyflow.model.OrderStatus;

import java.time.LocalDateTime;

public class OrderResponse {

    private final Long id;

    private final Long productId;

    private final Long supplierId;

    private final int quantity;

    private final OrderStatus status;

    private final LocalDateTime createdAt;


    public OrderResponse(
            Long id,
            Long productId,
            Long supplierId,
            int quantity,
            OrderStatus status,
            LocalDateTime createdAt
    ) {

        this.id = id;
        this.productId = productId;
        this.supplierId = supplierId;
        this.quantity = quantity;
        this.status = status;
        this.createdAt = createdAt;
    }


    public static OrderResponse from(Order order) {

        return new OrderResponse(
                order.getId(),
                order.getProductId(),
                order.getSupplierId(),
                order.getQuantity(),
                order.getStatus(),
                order.getCreatedAt()
        );
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
}