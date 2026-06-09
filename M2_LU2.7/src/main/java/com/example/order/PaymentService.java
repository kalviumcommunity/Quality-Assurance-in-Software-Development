package com.example.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Records payment authorisations.
 *
 * <p>State is held in-memory. Provides {@link #getAuthorisationCount()} so
 * integration tests can assert that no payment was attempted when stock was
 * insufficient (the PaymentService-was-never-called assertion).
 */
public class PaymentService {

    private final List<String> authorisedOrderIds = new ArrayList<>();

    /**
     * Records a payment authorisation for {@code orderId}.
     *
     * @throws PaymentFailedException if {@code amount} is not positive
     */
    public void authorise(String orderId, double amount) {
        if (amount <= 0) {
            throw new PaymentFailedException("Invalid payment amount: " + amount);
        }
        authorisedOrderIds.add(orderId);
    }

    /**
     * Returns {@code true} if {@code orderId} has been authorised.
     */
    public boolean wasAuthorised(String orderId) {
        return authorisedOrderIds.contains(orderId);
    }

    /**
     * Returns the total number of authorisations recorded since construction
     * (or since the last {@link #reset}).
     */
    public int getAuthorisationCount() {
        return authorisedOrderIds.size();
    }

    /**
     * Clears all authorisation records. Used in test {@code @BeforeEach} to
     * guarantee a clean baseline across tests.
     */
    public void reset() {
        authorisedOrderIds.clear();
    }
}
