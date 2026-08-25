package com.supplyflow;

import com.supplyflow.controller.StockMovementController;
import com.supplyflow.model.StockMovement;
import com.supplyflow.model.StockMovementType;
import com.supplyflow.service.StockMovementService;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(StockMovementController.class)
public class StockMovementControllerTest {

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private StockMovementService stockMovementService;


    // =========================
    // GET ALL STOCK MOVEMENTS
    // =========================

    @Test
    void shouldReturnAllStockMovements()
            throws Exception {

        StockMovement movement1 =
                new StockMovement(
                        1L,
                        5,
                        StockMovementType.STOCK_INCREASE
                );

        StockMovement movement2 =
                new StockMovement(
                        2L,
                        3,
                        StockMovementType.STOCK_DECREASE
                );


        when(
                stockMovementService.getAllMovements()
        ).thenReturn(
                List.of(
                        movement1,
                        movement2
                )
        );


        mockMvc.perform(
                        get("/stock-movements")
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
                        jsonPath("$[0].type")
                                .value("STOCK_INCREASE")
                );
    }


    // =========================
    // GET MOVEMENTS BY PRODUCT ID
    // =========================

    @Test
    void shouldReturnMovementsByProductId()
            throws Exception {

        StockMovement movement1 =
                new StockMovement(
                        1L,
                        5,
                        StockMovementType.STOCK_INCREASE
                );

        StockMovement movement2 =
                new StockMovement(
                        1L,
                        3,
                        StockMovementType.STOCK_DECREASE
                );


        when(
                stockMovementService
                        .getMovementsByProductId(1L)
        ).thenReturn(
                List.of(
                        movement1,
                        movement2
                )
        );


        mockMvc.perform(
                        get("/stock-movements/product/1")
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
                        jsonPath("$[1].type")
                                .value("STOCK_DECREASE")
                );
    }


    // =========================
    // CREATE STOCK MOVEMENT
    // =========================

    @Test
    void shouldCreateStockMovement()
            throws Exception {

        StockMovement movement =
                new StockMovement(
                        1L,
                        5,
                        StockMovementType.STOCK_INCREASE
                );


        when(
                stockMovementService.createMovement(
                        eq(1L),
                        eq(5),
                        eq(
                                StockMovementType
                                        .STOCK_INCREASE
                        )
                )
        ).thenReturn(movement);


        String requestBody = """
                {
                    "productId": 1,
                    "quantity": 5,
                    "type": "STOCK_INCREASE"
                }
                """;


        mockMvc.perform(
                        post("/stock-movements")
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
                        jsonPath("$.quantity")
                                .value(5)
                )
                .andExpect(
                        jsonPath("$.type")
                                .value("STOCK_INCREASE")
                );
    }

        // =========================
    // VALIDATION
    // =========================

    @Test
    void shouldReturnValidationErrorWhenQuantityIsZero()
            throws Exception {

        String requestBody = """
                {
                    "productId": 1,
                    "quantity": 0,
                    "type": "STOCK_INCREASE"
                }
                """;


        mockMvc.perform(
                        post("/stock-movements")
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )
                                .content(requestBody)
                )
                .andExpect(
                        status().isBadRequest()
                )
                .andExpect(
                        jsonPath("$.status")
                                .value(400)
                )
                .andExpect(
                        jsonPath("$.error")
                                .value("Validation Failed")
                )
                .andExpect(
                        jsonPath("$.errors.quantity")
                                .exists()
                );
    }

        @Test
    void shouldReturnBadRequestWhenTypeIsMissing()
            throws Exception {

        String requestBody = """
                {
                    "productId": 1,
                    "quantity": 5
                }
                """;


        mockMvc.perform(
                        post("/stock-movements")
                                .contentType(
                                        MediaType.APPLICATION_JSON
                                )
                                .content(requestBody)
                )
                .andExpect(
                        status().isBadRequest()
                );
    }
}