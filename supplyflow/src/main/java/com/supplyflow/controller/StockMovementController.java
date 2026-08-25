package com.supplyflow.controller;

import com.supplyflow.dto.StockMovementResponse;
import com.supplyflow.model.StockMovement;
import com.supplyflow.service.StockMovementService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.supplyflow.dto.CreateStockMovementRequest;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/stock-movements")
@Tag(
        name = "Stock Movements",
        description = "Stock increase, decrease and delivery history"
)
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
    public List<StockMovementResponse> getAllMovements() {

        return stockMovementService
                .getAllMovements()
                .stream()
                .map(StockMovementResponse::from)
                .toList();
    }


    // =========================
    // GET MOVEMENTS BY PRODUCT ID
    // =========================

    @GetMapping("/product/{productId}")
    public List<StockMovementResponse> getMovementsByProductId(
            @PathVariable("productId") Long productId
    ) {

        return stockMovementService
                .getMovementsByProductId(productId)
                .stream()
                .map(StockMovementResponse::from)
                .toList();
    }


    // =========================
    // CREATE STOCK MOVEMENT
    // =========================

    @PostMapping
        public ResponseEntity<StockMovementResponse> createMovement(
                @Valid
                @RequestBody CreateStockMovementRequest request
        ) {

        StockMovement createdMovement =
                stockMovementService.createMovement(
                        request.getProductId(),
                        request.getQuantity(),
                        request.getType()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        StockMovementResponse.from(createdMovement)
                );
        }
}