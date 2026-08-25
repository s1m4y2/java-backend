package com.supplyflow.repository;

import com.supplyflow.model.StockMovement;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockMovementRepository
        extends JpaRepository<StockMovement, Long> {

    List<StockMovement> findByProductId(
            Long productId
    );

}