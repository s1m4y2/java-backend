package com.supplyflow.controller;

import com.supplyflow.model.StockMovement;
import com.supplyflow.service.StockMovementService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock-movements")
public class StockMovementController {

    private final StockMovementService stockMovementService;


    public StockMovementController(
            StockMovementService stockMovementService
    ) {
        this.stockMovementService =
                stockMovementService;
    }


    // =========================
    // GET ALL STOCK MOVEMENTS
    // =========================

    @GetMapping
    public List<StockMovement> getAllMovements() {

        return stockMovementService
                .getAllMovements();
    }


    // =========================
    // GET MOVEMENTS BY PRODUCT ID
    // =========================

    @GetMapping("/product/{productId}")
    public List<StockMovement> getMovementsByProductId(
            @PathVariable("productId") Long productId
    ) {

        return stockMovementService
                .getMovementsByProductId(productId);
    }
}