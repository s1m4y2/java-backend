package com.supplyflow;

import com.supplyflow.exception.OrderNotFoundException;
import com.supplyflow.model.Order;
import com.supplyflow.model.OrderStatus;
import com.supplyflow.model.OrderSuggestion;
import com.supplyflow.model.Product;
import com.supplyflow.model.Supplier;
import com.supplyflow.repository.OrderRepository;
import com.supplyflow.service.OrderService;
import com.supplyflow.service.ProductService;
import com.supplyflow.service.SupplierService;
import com.supplyflow.model.StockMovementType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;


public class OrderServiceTest {

    private OrderService orderService;

    private OrderRepository orderRepository;
    private ProductService productService;
    private SupplierService supplierService;


    @BeforeEach
    void setUp() {

        orderRepository =
                mock(OrderRepository.class);

        productService =
                mock(ProductService.class);

        supplierService =
                mock(SupplierService.class);


        orderService =
                new OrderService(
                        orderRepository,
                        productService,
                        supplierService
                );
    }


    // =========================
    // CREATE ORDER
    // =========================

    @Test
    void shouldCreateOrder() {

        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );

        Product product = new Product(
                1L,
                "Laptop",
                45000.0,
                10,
                5,
                supplier
        );


        when(
                productService.getProductById(1L)
        ).thenReturn(product);

        when(
                supplierService.getSupplierById(1L)
        ).thenReturn(supplier);


        when(
                orderRepository.save(any(Order.class))
        ).thenAnswer(
                invocation -> invocation.getArgument(0)
        );


        Order order =
                orderService.createOrder(
                        1L,
                        1L,
                        5
                );


        assertEquals(
                1L,
                order.getProductId()
        );

        assertEquals(
                1L,
                order.getSupplierId()
        );

        assertEquals(
                5,
                order.getQuantity()
        );

        assertEquals(
                OrderStatus.CREATED,
                order.getStatus()
        );


        verify(orderRepository)
                .save(any(Order.class));
    }


    // =========================
    // ORDER NOT FOUND
    // =========================

    @Test
    void shouldThrowExceptionWhenOrderNotFound() {

        when(
                orderRepository.findById(999L)
        ).thenReturn(
                Optional.empty()
        );


        assertThrows(
                OrderNotFoundException.class,
                () -> orderService.getOrderById(999L)
        );
    }


    // =========================
    // APPROVE ORDER
    // =========================

    @Test
    void shouldApproveOrder() {

        Order order = new Order(
                1L,
                1L,
                5
        );


        when(
                orderRepository.findById(1L)
        ).thenReturn(
                Optional.of(order)
        );


        when(
                orderRepository.save(order)
        ).thenReturn(order);


        Order approvedOrder =
                orderService.approveOrder(1L);


        assertEquals(
                OrderStatus.APPROVED,
                approvedOrder.getStatus()
        );


        verify(orderRepository)
                .save(order);
    }


    // =========================
    // CANCEL ORDER
    // =========================

    @Test
    void shouldCancelOrder() {

        Order order = new Order(
                1L,
                1L,
                5
        );


        when(
                orderRepository.findById(1L)
        ).thenReturn(
                Optional.of(order)
        );


        when(
                orderRepository.save(order)
        ).thenReturn(order);


        Order cancelledOrder =
                orderService.cancelOrder(1L);


        assertEquals(
                OrderStatus.CANCELLED,
                cancelledOrder.getStatus()
        );


        verify(orderRepository)
                .save(order);
    }


    // =========================
    // DELIVER ORDER
    // =========================

    @Test
    void shouldDeliverApprovedOrder() {

        Order order = new Order(
                1L,
                1L,
                5
        );

        order.approve();


        when(
                orderRepository.findById(1L)
        ).thenReturn(
                Optional.of(order)
        );


        when(
                orderRepository.save(order)
        ).thenReturn(order);


        Order deliveredOrder =
                orderService.deliverOrder(1L);


        assertEquals(
                OrderStatus.DELIVERED,
                deliveredOrder.getStatus()
        );


        verify(productService)
                .increaseStock(
                        1L,
                        5,
                        StockMovementType.ORDER_DELIVERED
                );


        verify(orderRepository)
                .save(order);
    }


    // =========================
    // INVALID QUANTITY
    // =========================

    @Test
    void shouldThrowExceptionWhenQuantityIsZero() {

        assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder(
                        1L,
                        1L,
                        0
                )
        );


        verifyNoInteractions(
                productService,
                supplierService
        );

        verify(
                orderRepository,
                never()
        ).save(any());
    }


    // =========================
    // WRONG SUPPLIER
    // =========================

    @Test
    void shouldThrowExceptionWhenSupplierDoesNotMatchProduct() {

        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );

        Product product = new Product(
                1L,
                "Laptop",
                45000.0,
                10,
                5,
                supplier
        );


        when(
                productService.getProductById(1L)
        ).thenReturn(product);

        when(
                supplierService.getSupplierById(2L)
        ).thenReturn(supplier);


        assertThrows(
                IllegalArgumentException.class,
                () -> orderService.createOrder(
                        1L,
                        2L,
                        5
                )
        );


        verify(
                orderRepository,
                never()
        ).save(any());
    }


    // =========================
    // CREATE FROM SUGGESTION
    // =========================

    @Test
    void shouldCreateOrderFromSuggestion() {

        Supplier supplier = new Supplier(
                1L,
                "Tech Supplier",
                "info@techsupplier.com",
                "555-111-2233"
        );

        Product product = new Product(
                1L,
                "Laptop",
                45000.0,
                10,
                5,
                supplier
        );

        OrderSuggestion suggestion =
                new OrderSuggestion(
                        1L,
                        1L,
                        8
                );


        when(
                productService.getProductById(1L)
        ).thenReturn(product);

        when(
                supplierService.getSupplierById(1L)
        ).thenReturn(supplier);


        when(
                orderRepository.save(any(Order.class))
        ).thenAnswer(
                invocation -> invocation.getArgument(0)
        );


        Order order =
                orderService
                        .createOrderFromSuggestion(
                                suggestion
                        );


        assertEquals(
                1L,
                order.getProductId()
        );

        assertEquals(
                1L,
                order.getSupplierId()
        );

        assertEquals(
                8,
                order.getQuantity()
        );


        verify(orderRepository)
                .save(any(Order.class));
    }


    // =========================
    // GET ALL ORDERS
    // =========================

    @Test
    void shouldReturnAllOrders() {

        Order order1 = new Order(
                1L,
                1L,
                5
        );

        Order order2 = new Order(
                2L,
                2L,
                10
        );


        when(
                orderRepository.findAll()
        ).thenReturn(
                List.of(
                        order1,
                        order2
                )
        );


        List<Order> orders =
                orderService.getAllOrders();


        assertEquals(
                2,
                orders.size()
        );


        assertSame(
                order1,
                orders.get(0)
        );

        assertSame(
                order2,
                orders.get(1)
        );
    }

    // =========================
        // DO NOT CREATE DUPLICATE
        // OPEN ORDER
        // =========================

        @Test
        void shouldNotCreateDuplicateOrderForProductWithOpenOrder() {

        OrderSuggestion suggestion =
                new OrderSuggestion(
                        1L,
                        1L,
                        10
                );

        when(
                productService.getAllOrderSuggestions()
        ).thenReturn(
                List.of(suggestion)
        );

        when(
                orderRepository
                        .existsByProductIdAndStatusIn(
                                eq(1L),
                                any()
                        )
        ).thenReturn(true);


        List<Order> result =
                orderService
                        .createOrdersForCriticalProducts();


        assertTrue(
                result.isEmpty()
        );

        verify(
                orderRepository,
                never()
        ).save(
                any(Order.class)
        );
        }

        // =========================
        // CANNOT DELIVER
        // UNAPPROVED ORDER
        // =========================

        @Test
        void shouldThrowExceptionWhenDeliveringCreatedOrder() {

        Order order =
                new Order(
                        1L,
                        1L,
                        5
                );


        when(
                orderRepository.findById(1L)
        ).thenReturn(
                Optional.of(order)
        );


        assertThrows(
                IllegalStateException.class,
                () -> orderService.deliverOrder(1L)
        );


        verify(
                productService,
                never()
        ).increaseStock(
                any(),
                anyInt(),
                any()
        );


        verify(
                orderRepository,
                never()
        ).save(
                any(Order.class)
        );
        }
}