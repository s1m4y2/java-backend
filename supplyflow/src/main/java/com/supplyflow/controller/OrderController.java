package com.supplyflow.controller;

import jakarta.validation.Valid;

import com.supplyflow.dto.CreateOrderRequest;

import com.supplyflow.model.Order;
import com.supplyflow.model.OrderSuggestion;

import com.supplyflow.service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;


    public OrderController(
            OrderService orderService
    ) {
        this.orderService = orderService;
    }


    // =========================
    // GET ALL ORDERS
    // =========================

    @GetMapping
    public List<Order> getAllOrders() {

        return orderService.getAllOrders();
    }


    // =========================
    // GET ORDER BY ID
    // =========================

    @GetMapping("/{id}")
    public Order getOrderById(
            @PathVariable("id") Long id
    ) {

        return orderService.getOrderById(id);
    }


    // =========================
    // CREATE ORDER
    // =========================

    @PostMapping
    public ResponseEntity<Order> createOrder(
            @Valid @RequestBody CreateOrderRequest request
    ) {

        Order order =
                orderService.createOrder(
                        request.getProductId(),
                        request.getSupplierId(),
                        request.getQuantity()
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(order);
    }


    // =========================
    // APPROVE ORDER
    // =========================

    @PutMapping("/{id}/approve")
    public ResponseEntity<Order> approveOrder(
            @PathVariable("id") Long id
    ) {

        Order order =
                orderService.approveOrder(id);

        return ResponseEntity.ok(order);
    }


    // =========================
    // DELIVER ORDER
    // =========================

    @PutMapping("/{id}/deliver")
    public ResponseEntity<Order> deliverOrder(
            @PathVariable("id") Long id
    ) {

        Order order =
                orderService.deliverOrder(id);

        return ResponseEntity.ok(order);
    }


    // =========================
    // CANCEL ORDER
    // =========================

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Order> cancelOrder(
            @PathVariable("id") Long id
    ) {

        Order order =
                orderService.cancelOrder(id);

        return ResponseEntity.ok(order);
    }


    // =========================
    // CREATE ORDER FROM SUGGESTION
    // =========================

    @PostMapping("/from-suggestion")
    public ResponseEntity<Order> createOrderFromSuggestion(
            @RequestBody OrderSuggestion suggestion
    ) {

        Order order =
                orderService.createOrderFromSuggestion(
                        suggestion
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(order);
    }


    // =========================
    // CREATE ORDERS FOR CRITICAL PRODUCTS
    // =========================

    @PostMapping("/critical-products")
    public ResponseEntity<List<Order>>
    createOrdersForCriticalProducts() {

        List<Order> orders =
                orderService.createOrdersForCriticalProducts();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orders);
    }
}