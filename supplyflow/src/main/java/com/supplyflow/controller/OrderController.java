package com.supplyflow.controller;

import jakarta.validation.Valid;

import com.supplyflow.dto.CreateOrderRequest;

import com.supplyflow.model.Order;
import com.supplyflow.model.OrderSuggestion;
import com.supplyflow.dto.OrderResponse;
import com.supplyflow.service.OrderService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@Tag(
        name = "Orders",
        description = "Purchase order management and lifecycle operations"
)
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
        public List<OrderResponse> getAllOrders() {

        return orderService
                .getAllOrders()
                .stream()
                .map(OrderResponse::from)
                .toList();
        }


    // =========================
    // GET ORDER BY ID
    // =========================

    @GetMapping("/{id}")
        public OrderResponse getOrderById(
                @PathVariable("id") Long id
        ) {

        Order order =
                orderService.getOrderById(id);

        return OrderResponse.from(order);
        }


    // =========================
    // CREATE ORDER
    // =========================

    @PostMapping
        public ResponseEntity<OrderResponse> createOrder(
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
                .body(OrderResponse.from(order));
        }


    // =========================
    // APPROVE ORDER
    // =========================

    @PutMapping("/{id}/approve")
        public ResponseEntity<OrderResponse> approveOrder(
                @PathVariable("id") Long id
        ) {

        Order order =
                orderService.approveOrder(id);

        return ResponseEntity.ok(
                OrderResponse.from(order)
        );
        }


    // =========================
    // DELIVER ORDER
    // =========================

    @PutMapping("/{id}/deliver")
        public ResponseEntity<OrderResponse> deliverOrder(
                @PathVariable("id") Long id
        ) {

        Order order =
                orderService.deliverOrder(id);

        return ResponseEntity.ok(
                OrderResponse.from(order)
        );
        }


    // =========================
    // CANCEL ORDER
    // =========================

    @PutMapping("/{id}/cancel")
        public ResponseEntity<OrderResponse> cancelOrder(
                @PathVariable("id") Long id
        ) {

        Order order =
                orderService.cancelOrder(id);

        return ResponseEntity.ok(
                OrderResponse.from(order)
        );
        }


    // =========================
    // CREATE ORDER FROM SUGGESTION
    // =========================

    @PostMapping("/from-suggestion")
        public ResponseEntity<OrderResponse> createOrderFromSuggestion(
                @RequestBody OrderSuggestion suggestion
        ) {

        Order order =
                orderService.createOrderFromSuggestion(
                        suggestion
                );

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        OrderResponse.from(order)
                );
        }


    // =========================
    // CREATE ORDERS FOR CRITICAL PRODUCTS
    // =========================

    @PostMapping("/critical-products")
        public ResponseEntity<List<OrderResponse>>
        createOrdersForCriticalProducts() {

        List<OrderResponse> orders =
                orderService
                        .createOrdersForCriticalProducts()
                        .stream()
                        .map(OrderResponse::from)
                        .toList();

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(orders);
        }
}