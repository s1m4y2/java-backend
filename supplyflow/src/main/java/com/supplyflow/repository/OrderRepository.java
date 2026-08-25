package com.supplyflow.repository;

import com.supplyflow.model.Order;
import com.supplyflow.model.OrderStatus;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface OrderRepository
        extends JpaRepository<Order, Long> {

    boolean existsByProductIdAndStatusIn(
            Long productId,
            Collection<OrderStatus> statuses
    );

}