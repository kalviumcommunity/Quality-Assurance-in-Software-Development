package com.example.order;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Integration test suite for the OrderProcessor → InventoryService → PaymentService chain.
 *
 * <p><strong>QA risk reduced:</strong> TODO — describe what defect class this suite catches.
 *
 * <p><strong>Limitation that remains:</strong> TODO — describe one scenario NOT covered.
 */
class OrderProcessorIntegrationTest {

    private InventoryService inventoryService;
    private PaymentService   paymentService;
    private OrderProcessor   orderProcessor;

    private static final String PRODUCT_ID    = "WIDGET-001";
    private static final int    INITIAL_STOCK = 5;

    @BeforeEach
    void setUp() {
        // TODO: Initialise real instances of InventoryService, PaymentService, and
        //       OrderProcessor. Seed PRODUCT_ID with INITIAL_STOCK units.
    }

    @AfterEach
    void tearDown() {
        // No shared static state — nothing to clean up. This method documents intent.
    }

    // ── Test 1: Happy-path ────────────────────────────────────────────────────

    @Test
    void placeOrder_withSufficientStock_confirmsOrderAndDecrementsStock() {
        // TODO:
        //  Arrange — create an Order for PRODUCT_ID, quantity 2, amount 199.99
        //  Act     — call orderProcessor.placeOrder(order)
        //  Assert  — result.isConfirmed() is true
        //          — paymentService.wasAuthorised(result.getOrderId()) is true
        //          — inventoryService.getStock(PRODUCT_ID) equals INITIAL_STOCK - 2
    }

    // ── Test 2: Stub-driven negative ──────────────────────────────────────────

    @Test
    void placeOrder_withInsufficientStock_stubInventoryService_throwsOutOfStockAndSkipsPayment() {
        // TODO:
        //  Arrange — create a stub InventoryService that overrides checkStock to always
        //            throw OutOfStockException; wire it into a new OrderProcessor with
        //            the real paymentService
        //  Act & Assert — assertThrows(OutOfStockException.class, ...)
        //               — assertEquals(0, paymentService.getAuthorisationCount(), ...)
    }
}
