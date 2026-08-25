package com.supplyflow;

import com.supplyflow.model.StockMovement;
import com.supplyflow.model.StockMovementType;
import com.supplyflow.repository.StockMovementRepository;
import com.supplyflow.service.StockMovementService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class StockMovementServiceTest {

    private StockMovementService stockMovementService;

    private StockMovementRepository stockMovementRepository;


    @BeforeEach
    void setUp() {

        stockMovementRepository =
                mock(
                        StockMovementRepository.class
                );

        stockMovementService =
                new StockMovementService(
                        stockMovementRepository
                );
    }


    // =========================
    // CREATE STOCK MOVEMENT
    // =========================

    @Test
    void shouldCreateStockMovement() {

        StockMovement savedMovement =
                new StockMovement(
                        1L,
                        5,
                        StockMovementType.STOCK_INCREASE
                );

        when(
                stockMovementRepository.save(
                        any(StockMovement.class)
                )
        ).thenReturn(
                savedMovement
        );


        StockMovement result =
                stockMovementService.createMovement(
                        1L,
                        5,
                        StockMovementType.STOCK_INCREASE
                );


        assertEquals(
                StockMovementType.STOCK_INCREASE,
                result.getType()
        );

        assertEquals(
                5,
                result.getQuantity()
        );

        assertEquals(
                1L,
                result.getProductId()
        );


        verify(
                stockMovementRepository
        ).save(
                any(StockMovement.class)
        );
    }


    // =========================
    // GET ALL MOVEMENTS
    // =========================

    @Test
    void shouldReturnAllMovements() {

        StockMovement movement1 =
                new StockMovement(
                        1L,
                        5,
                        StockMovementType.STOCK_INCREASE
                );

        StockMovement movement2 =
                new StockMovement(
                        2L,
                        10,
                        StockMovementType.STOCK_DECREASE
                );


        when(
                stockMovementRepository.findAll()
        ).thenReturn(
                List.of(
                        movement1,
                        movement2
                )
        );


        List<StockMovement> movements =
                stockMovementService
                        .getAllMovements();


        assertEquals(
                2,
                movements.size()
        );


        verify(
                stockMovementRepository
        ).findAll();
    }


    // =========================
    // GET MOVEMENTS BY PRODUCT ID
    // =========================

    @Test
    void shouldReturnMovementsByProductId() {

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
                stockMovementRepository
                        .findByProductId(1L)
        ).thenReturn(
                List.of(
                        movement1,
                        movement2
                )
        );


        List<StockMovement> movements =
                stockMovementService
                        .getMovementsByProductId(
                                1L
                        );


        assertEquals(
                2,
                movements.size()
        );

        assertEquals(
                1L,
                movements.get(0)
                        .getProductId()
        );

        assertEquals(
                1L,
                movements.get(1)
                        .getProductId()
        );


        verify(
                stockMovementRepository
        ).findByProductId(1L);
    }
}