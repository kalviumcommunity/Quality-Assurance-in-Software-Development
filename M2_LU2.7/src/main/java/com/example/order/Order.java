package com.example.order;

/**
 * Immutable value object representing a customer order request.
 */
public class Order {

    private final String productId;
    private final int quantity;
    private final double amount;

    public Order(String productId, int quantity, double amount) {
        if (productId == null || productId.isBlank()) {
            throw new IllegalArgumentException("productId must not be blank");
        }
        if (quantity < 1) {
            throw new IllegalArgumentException("quantity must be at least 1, got: " + quantity);
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("amount must be positive, got: " + amount);
        }
        this.productId = productId;
        this.quantity = quantity;
        this.amount = amount;
    }

    public String getProductId() { return productId; }
    public int getQuantity()     { return quantity; }
    public double getAmount()    { return amount; }

    @Override
    public String toString() {
        return "Order{productId='" + productId + "', quantity=" + quantity + ", amount=" + amount + "}";
    }
}
