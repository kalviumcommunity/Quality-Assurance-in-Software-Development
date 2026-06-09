package com.example.order;

/**
 * Thrown by {@link InventoryService#checkStock} when the requested quantity
 * exceeds available stock. OrderProcessor catches this to short-circuit the
 * order pipeline before any payment authorisation is attempted.
 */
public class OutOfStockException extends RuntimeException {

    public OutOfStockException(String message) {
        super(message);
    }
}
