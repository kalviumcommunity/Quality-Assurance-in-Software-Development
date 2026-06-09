package com.example.order;

import java.util.UUID;

/**
 * Orchestrates the order pipeline:
 * <ol>
 *   <li>Check stock via {@link InventoryService#checkStock} — short-circuits on
 *       {@link OutOfStockException} before any payment attempt.</li>
 *   <li>Authorise payment via {@link PaymentService#authorise}.</li>
 *   <li>Decrement stock via {@link InventoryService#decrementStock} — only after
 *       a successful payment authorisation.</li>
 * </ol>
 *
 * <p>Both services are injected via the constructor, making it straightforward to
 * wire real instances in integration tests or supply a stub InventoryService for
 * negative-path testing without touching the payment path.
 */
public class OrderProcessor {

    private final InventoryService inventoryService;
    private final PaymentService   paymentService;

    public OrderProcessor(InventoryService inventoryService, PaymentService paymentService) {
        this.inventoryService = inventoryService;
        this.paymentService   = paymentService;
    }

    /**
     * Places the order and returns a confirmed {@link OrderResult}.
     *
     * @throws OutOfStockException    if the requested quantity exceeds available stock
     * @throws PaymentFailedException if the payment gateway declines the charge
     */
    public OrderResult placeOrder(Order order) {
        // Step 1 — Guard: throws OutOfStockException if stock is insufficient.
        // PaymentService is never called if this throws.
        inventoryService.checkStock(order.getProductId(), order.getQuantity());

        // Step 2 — Authorise payment
        String orderId = UUID.randomUUID().toString();
        paymentService.authorise(orderId, order.getAmount());

        // Step 3 — Decrement stock only after successful payment
        inventoryService.decrementStock(order.getProductId(), order.getQuantity());

        return new OrderResult(orderId, true);
    }
}
