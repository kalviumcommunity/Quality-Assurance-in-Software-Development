package com.example.order;

/**
 * Immutable value object returned by a successful order placement.
 */
public class OrderResult {

    private final String orderId;
    private final boolean confirmed;

    public OrderResult(String orderId, boolean confirmed) {
        this.orderId = orderId;
        this.confirmed = confirmed;
    }

    public String getOrderId()   { return orderId; }
    public boolean isConfirmed() { return confirmed; }

    @Override
    public String toString() {
        return "OrderResult{orderId='" + orderId + "', confirmed=" + confirmed + "}";
    }
}
