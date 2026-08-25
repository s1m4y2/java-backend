package com.supplyflow.service;

import com.supplyflow.model.StockMovement;
import com.supplyflow.model.StockMovementType;
import com.supplyflow.repository.StockMovementRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockMovementService {

    private final StockMovementRepository stockMovementRepository;

    public StockMovementService(
            StockMovementRepository stockMovementRepository
    ) {
        this.stockMovementRepository =
                stockMovementRepository;
    }

    public StockMovement createMovement(
                Long productId,
                int quantity,
                StockMovementType type
        ) {

        if (productId == null) {

                throw new IllegalArgumentException(
                        "Product ID is required."
                );
        }

        if (quantity <= 0) {

                throw new IllegalArgumentException(
                        "Quantity must be greater than zero."
                );
        }

        if (type == null) {

                throw new IllegalArgumentException(
                        "Stock movement type is required."
                );
        }

        StockMovement movement =
                new StockMovement(
                        productId,
                        quantity,
                        type
                );

        return stockMovementRepository.save(
                movement
        );
        }

    public List<StockMovement> getAllMovements() {

        return stockMovementRepository.findAll();
    }

    public List<StockMovement> getMovementsByProductId(
            Long productId
    ) {

        return stockMovementRepository
                .findByProductId(productId);
    }
}