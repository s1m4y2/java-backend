package com.supplyflow;

import com.supplyflow.controller.OrderController;
import com.supplyflow.exception.GlobalExceptionHandler;
import com.supplyflow.model.Order;
import com.supplyflow.model.OrderStatus;
import com.supplyflow.service.OrderService;
import com.supplyflow.model.OrderSuggestion;
import com.supplyflow.exception.OrderNotFoundException;
import static org.mockito.ArgumentMatchers.any;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

import static org.mockito.ArgumentMatchers.eq;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.context.annotation.Import;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(OrderController.class)
@Import(GlobalExceptionHandler.class)
public class OrderControllerTest {


    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private OrderService orderService;


    // =========================
    // GET ALL ORDERS
    // =========================

    @Test
    void shouldReturnAllOrders()
            throws Exception {

        Order order1 =
                new Order(
                        1L,
                        1L,
                        5
                );

        Order order2 =
                new Order(
                        2L,
                        2L,
                        10
                );


        when(
                orderService.getAllOrders()
        ).thenReturn(
                List.of(
                        order1,
                        order2
                )
        );


        mockMvc.perform(
                        get("/orders")
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$.length()")
                                .value(2)
                )
                .andExpect(
                        jsonPath("$[0].productId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$[0].quantity")
                                .value(5)
                )
                .andExpect(
                        jsonPath("$[1].productId")
                                .value(2)
                )
                .andExpect(
                        jsonPath("$[1].quantity")
                                .value(10)
                );
    }

    // =========================
    // GET ORDER BY ID
    // =========================

    @Test
    void shouldReturnOrderById()
            throws Exception {

        Order order =
                new Order(
                        1L,
                        1L,
                        5
                );


        when(
                orderService.getOrderById(1L)
        ).thenReturn(order);


        mockMvc.perform(
                        get("/orders/1")
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$.productId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.supplierId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.quantity")
                                .value(5)
                )
                .andExpect(
                        jsonPath("$.status")
                                .value("CREATED")
                );
    }

    // =========================
    // CREATE ORDER
    // =========================

    @Test
    void shouldCreateOrder()
            throws Exception {

        Order order =
                new Order(
                        1L,
                        1L,
                        5
                );


        when(
                orderService.createOrder(
                        eq(1L),
                        eq(1L),
                        eq(5)
                )
        ).thenReturn(order);


        String requestBody =
                """
                {
                    "productId": 1,
                    "supplierId": 1,
                    "quantity": 5
                }
                """;


        mockMvc.perform(
                        post("/orders")
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )
                                .content(requestBody)
                )
                .andExpect(
                        status().isCreated()
                )
                .andExpect(
                        jsonPath("$.productId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.supplierId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.quantity")
                                .value(5)
                )
                .andExpect(
                        jsonPath("$.status")
                                .value("CREATED")
                );
    }

    // =========================
    // APPROVE ORDER
    // =========================

    @Test
    void shouldApproveOrder()
            throws Exception {

        Order order =
                new Order(
                        1L,
                        1L,
                        5
                );

        order.approve();


        when(
                orderService.approveOrder(1L)
        ).thenReturn(order);


        mockMvc.perform(
                        put("/orders/1/approve")
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$.productId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.supplierId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.quantity")
                                .value(5)
                )
                .andExpect(
                        jsonPath("$.status")
                                .value("APPROVED")
                );
    }

    // =========================
    // DELIVER ORDER
    // =========================

    @Test
    void shouldDeliverOrder()
            throws Exception {

        Order order =
                new Order(
                        1L,
                        1L,
                        5
                );

        order.approve();
        order.deliver();


        when(
                orderService.deliverOrder(1L)
        ).thenReturn(order);


        mockMvc.perform(
                        put("/orders/1/deliver")
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$.productId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.supplierId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.quantity")
                                .value(5)
                )
                .andExpect(
                        jsonPath("$.status")
                                .value("DELIVERED")
                );
    }

    // =========================
    // CANCEL ORDER
    // =========================

    @Test
    void shouldCancelOrder()
            throws Exception {

        Order order =
                new Order(
                        1L,
                        1L,
                        5
                );

        order.cancel();


        when(
                orderService.cancelOrder(1L)
        ).thenReturn(order);


        mockMvc.perform(
                        put("/orders/1/cancel")
                )
                .andExpect(
                        status().isOk()
                )
                .andExpect(
                        jsonPath("$.productId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.supplierId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.quantity")
                                .value(5)
                )
                .andExpect(
                        jsonPath("$.status")
                                .value("CANCELLED")
                );
    }

    // =========================
    // CREATE ORDER FROM SUGGESTION
    // =========================

    @Test
    void shouldCreateOrderFromSuggestion()
            throws Exception {

        OrderSuggestion suggestion =
                new OrderSuggestion(
                        1L,
                        1L,
                        5
                );

        Order order =
                new Order(
                        1L,
                        1L,
                        5
                );

        when(
                orderService.createOrderFromSuggestion(
                        any(OrderSuggestion.class)
                )
        ).thenReturn(order);


        String requestBody =
                """
                {
                    "productId": 1,
                    "supplierId": 1,
                    "recommendedQuantity": 5
                }
                """;


        mockMvc.perform(
                        post("/orders/from-suggestion")
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )
                                .content(requestBody)
                )
                .andExpect(
                        status().isCreated()
                )
                .andExpect(
                        jsonPath("$.productId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.supplierId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$.quantity")
                                .value(5)
                )
                .andExpect(
                        jsonPath("$.status")
                                .value("CREATED")
                );
    }

    // =========================
    // CREATE ORDERS FOR CRITICAL PRODUCTS
    // =========================

    @Test
    void shouldCreateOrdersForCriticalProducts()
            throws Exception {

        Order order1 =
                new Order(
                        1L,
                        1L,
                        13
                );

        Order order2 =
                new Order(
                        2L,
                        2L,
                        8
                );


        when(
                orderService
                        .createOrdersForCriticalProducts()
        ).thenReturn(
                List.of(
                        order1,
                        order2
                )
        );


        mockMvc.perform(
                        post("/orders/critical-products")
                )
                .andExpect(
                        status().isCreated()
                )
                .andExpect(
                        jsonPath("$.length()")
                                .value(2)
                )
                .andExpect(
                        jsonPath("$[0].productId")
                                .value(1)
                )
                .andExpect(
                        jsonPath("$[0].quantity")
                                .value(13)
                )
                .andExpect(
                        jsonPath("$[0].status")
                                .value("CREATED")
                )
                .andExpect(
                        jsonPath("$[1].productId")
                                .value(2)
                )
                .andExpect(
                        jsonPath("$[1].quantity")
                                .value(8)
                )
                .andExpect(
                        jsonPath("$[1].status")
                                .value("CREATED")
                );
    }

    // =========================
    // ORDER NOT FOUND
    // =========================

    @Test
    void shouldReturnNotFoundWhenOrderDoesNotExist()
            throws Exception {

        when(
                orderService.getOrderById(999L)
        ).thenThrow(
                new OrderNotFoundException(999L)
        );


        mockMvc.perform(
                        get("/orders/999")
                )
                .andExpect(
                        status().isNotFound()
                );
    }

    // =========================
    // INVALID ORDER REQUEST
    // =========================

    @Test
    void shouldReturnBadRequestWhenQuantityIsInvalid()
            throws Exception {

        String requestBody =
                """
                {
                    "productId": 1,
                    "supplierId": 1,
                    "quantity": 0
                }
                """;


        mockMvc.perform(
                        post("/orders")
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )
                                .content(requestBody)
                )
                .andExpect(
                        status().isBadRequest()
                );
    }

    // =========================
    // INVALID ORDER STATE
    // =========================

    @Test
    void shouldReturnBadRequestWhenDeliveringUnapprovedOrder()
            throws Exception {

        when(
                orderService.deliverOrder(1L)
        ).thenThrow(
                new IllegalStateException(
                        "Only APPROVED orders can be delivered."
                )
        );


        mockMvc.perform(
                        put("/orders/1/deliver")
                )
                .andExpect(
                        status().isBadRequest()
                );
    }
    
}