package com.example.order;

/**
 * Thrown by {@link PaymentService#authorise} when the payment gateway
 * declines the transaction. If this is thrown, stock decrement must NOT occur.
 */
public class PaymentFailedException extends RuntimeException {

    public PaymentFailedException(String message) {
        super(message);
    }
}
