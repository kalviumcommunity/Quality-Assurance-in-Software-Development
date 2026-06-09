package com.example.order;

import java.util.HashMap;
import java.util.Map;

/**
 * Manages product stock levels.
 *
 * <p>State is held in-memory. Call {@link #setStock} in test {@code @BeforeEach}
 * to initialise a clean baseline before each test.
 */
public class InventoryService {

    private final Map<String, Integer> stock = new HashMap<>();

    /**
     * Seeds stock for a product. Used in test setup.
     */
    public void setStock(String productId, int quantity) {
        stock.put(productId, quantity);
    }

    /**
     * Returns the current stock level for a product (0 if unknown).
     */
    public int getStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    /**
     * Asserts that {@code quantity} units are available for {@code productId}.
     *
     * @throws OutOfStockException if available stock is less than {@code quantity}
     */
    public void checkStock(String productId, int quantity) {
        int available = stock.getOrDefault(productId, 0);
        if (quantity > available) {
            throw new OutOfStockException(
                "Insufficient stock for product '" + productId
                    + "': requested " + quantity + ", available " + available
            );
        }
    }

    /**
     * Reduces stock by {@code quantity}. Must only be called after a successful
     * payment authorisation.
     */
    public void decrementStock(String productId, int quantity) {
        int current = stock.getOrDefault(productId, 0);
        stock.put(productId, current - quantity);
    }
}
