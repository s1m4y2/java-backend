package com.supplyflow.service;

import com.supplyflow.exception.OrderNotFoundException;
import com.supplyflow.model.Order;
import com.supplyflow.model.OrderStatus;
import com.supplyflow.model.OrderSuggestion;
import com.supplyflow.model.Product;
import com.supplyflow.model.StockMovementType;
import com.supplyflow.repository.OrderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;


@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final SupplierService supplierService;


    public OrderService(
            OrderRepository orderRepository,
            ProductService productService,
            SupplierService supplierService
    ) {

        this.orderRepository = orderRepository;
        this.productService = productService;
        this.supplierService = supplierService;
    }


    // =========================
    // CREATE ORDER
    // =========================

    public Order createOrder(
            Long productId,
            Long supplierId,
            int quantity
    ) {

        validateOrder(
                productId,
                supplierId,
                quantity
        );

        Order order = new Order(
                productId,
                supplierId,
                quantity
        );

        return orderRepository.save(order);
    }


    // =========================
    // CREATE ORDER - TEST / DIRECT USAGE
    // =========================

    public Order createOrder(Order order) {

        validateOrder(
                order.getProductId(),
                order.getSupplierId(),
                order.getQuantity()
        );

        return orderRepository.save(order);
    }


    // =========================
    // CREATE FROM SUGGESTION
    // =========================

    public Order createOrderFromSuggestion(
                OrderSuggestion suggestion
        ) {

        return createOrder(
                suggestion.getProductId(),
                suggestion.getSupplierId(),
                suggestion.getRecommendedQuantity()
        );
        }


    // =========================
    // GET ORDER BY ID
    // =========================

    @Transactional(readOnly = true)
    public Order getOrderById(Long id) {

        return orderRepository
                .findById(id)
                .orElseThrow(
                        () -> new OrderNotFoundException(id)
                );
    }


    // =========================
    // GET ALL ORDERS
    // =========================

    @Transactional(readOnly = true)
    public List<Order> getAllOrders() {

        return orderRepository.findAll();
    }


    // =========================
    // APPROVE ORDER
    // =========================

    public Order approveOrder(Long id) {

        Order order = getOrderById(id);

        order.approve();

        return orderRepository.save(order);
    }


    // =========================
    // DELIVER ORDER
    // =========================

    public Order deliverOrder(Long id) {

        Order order = getOrderById(id);

        order.deliver();

        productService.increaseStock(
                order.getProductId(),
                order.getQuantity(),
                StockMovementType.ORDER_DELIVERED
        );

        return orderRepository.save(order);
    }


    // =========================
    // CANCEL ORDER
    // =========================

    public Order cancelOrder(Long id) {

        Order order = getOrderById(id);

        order.cancel();

        return orderRepository.save(order);
    }


    // =========================
    // CREATE MULTIPLE ORDERS
    // =========================

    public List<Order> createOrdersFromSuggestions(
            List<OrderSuggestion> suggestions
    ) {

        List<Order> orders =
                new ArrayList<>();

        for (OrderSuggestion suggestion : suggestions) {

            Order order =
                    createOrderFromSuggestion(
                            suggestion
                    );

            orders.add(order);
        }

        return orders;
    }


    // =========================
    // CREATE ORDERS FOR CRITICAL PRODUCTS
    // =========================

    public List<Order> createOrdersForCriticalProducts() {

        List<OrderSuggestion> suggestions =
                productService.getAllOrderSuggestions();

        List<Order> orders =
                new ArrayList<>();

        for (OrderSuggestion suggestion : suggestions) {

                boolean hasOpenOrder =
                        orderRepository
                                .existsByProductIdAndStatusIn(
                                        suggestion.getProductId(),
                                        List.of(
                                                OrderStatus.CREATED,
                                                OrderStatus.APPROVED
                                        )
                                );

                if (hasOpenOrder) {
                continue;
                }

                Order order =
                        createOrderFromSuggestion(
                                suggestion
                        );

                orders.add(order);
        }

        return orders;
        }


    // =========================
    // VALIDATION
    // =========================

    private void validateOrder(
            Long productId,
            Long supplierId,
            int quantity
    ) {

        if (quantity <= 0) {

            throw new IllegalArgumentException(
                    "Quantity must be greater than zero."
            );
        }


        Product product =
                productService.getProductById(
                        productId
                );


        supplierService.getSupplierById(
                supplierId
        );


        if (!product.getSupplier()
                .getId()
                .equals(supplierId)) {

            throw new IllegalArgumentException(
                    "The selected supplier does not supply this product."
            );
        }
    }
}