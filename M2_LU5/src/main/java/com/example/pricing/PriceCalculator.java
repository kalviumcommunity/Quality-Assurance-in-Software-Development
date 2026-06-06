package com.example.pricing;

/**
 * Calculates the total price for a line item.
 *
 * <p>Specification:
 * <ul>
 *   <li>{@code unitPrice} — non-negative integer in paise, 0 to 10,000,000 inclusive.</li>
 *   <li>{@code quantity}  — integer from 1 to 1,000 inclusive.</li>
 *   <li>Returns {@code unitPrice × quantity} as a {@code long}.</li>
 *   <li>Throws {@link IllegalArgumentException} when either input is outside its documented range.</li>
 * </ul>
 */
public class PriceCalculator {

    /**
     * Computes the total price for a line item.
     *
     * @param unitPrice price per unit in paise (0 – 10,000,000)
     * @param quantity  number of units (1 – 1,000)
     * @return {@code unitPrice × quantity} as a {@code long}
     * @throws IllegalArgumentException if either parameter is outside its documented range
     */
    public long total(int unitPrice, int quantity) {
        if (unitPrice < 0 || unitPrice > 10_000_000)
            throw new IllegalArgumentException("unitPrice out of range: " + unitPrice);
        if (quantity < 1 || quantity > 1_000)
            throw new IllegalArgumentException("quantity out of range: " + quantity);
        return (long) unitPrice * quantity;
    }
}
