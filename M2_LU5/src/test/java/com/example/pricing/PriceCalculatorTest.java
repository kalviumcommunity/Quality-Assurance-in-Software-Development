package com.example.pricing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Your job: implement exactly SIX test executions for PriceCalculator.
 *
 * Required scope (from question.md):
 *
 *   Group A — @ParameterizedTest with @CsvSource (4 rows, all return a product):
 *     Row 1: valid mid-range         (unitPrice=500,      quantity=3,    expected=1500)
 *     Row 2: quantity lower boundary (unitPrice=500,      quantity=1,    expected=500)
 *     Row 3: quantity upper boundary (unitPrice=10,       quantity=1000, expected=10000)
 *     Row 4: combined upper-bound    (unitPrice=10000000, quantity=1000, expected=10000000000)
 *              ^ this row proves the implementation uses long arithmetic, not int.
 *              ^ 10,000,000 × 1,000 = 10,000,000,000 which overflows a 32-bit int.
 *
 *   Group B — two separate @Test methods using assertThrows:
 *     B1: quantity = 0    -> IllegalArgumentException (just below lower bound)
 *     B2: quantity = 1001 -> IllegalArgumentException (just above upper bound)
 *
 * Hard requirements:
 *   - Group A MUST be a single @ParameterizedTest with @CsvSource and a name attribute.
 *   - Group B tests MUST each use assertThrows(IllegalArgumentException.class, ...).
 *   - Naming convention: methodName_condition_expectedBehaviour
 *     (e.g. total_withQuantityZero_throwsIllegalArgumentException)
 *   - AAA structure visible in every method (comments or blank-line separation).
 *   - mvn test must print: Tests run: 6, Failures: 0, Errors: 0
 */
class PriceCalculatorTest {

    // -------------------------------------------------------------------------
    // TODO — Group A: one @ParameterizedTest with four CSV rows.
    //
    // The third column is the expected long result.
    // JUnit maps the CSV string "10000000000" to a long parameter automatically
    // (no L suffix needed in the CSV string — the declared parameter type drives parsing).
    // -------------------------------------------------------------------------

    // @ParameterizedTest(name = "total({0},{1}) should return {2}")
    // @CsvSource({
    //     "...", // row 1
    //     "...", // row 2
    //     "...", // row 3
    //     "..."  // row 4 — combined upper-bound
    // })
    // void total_withValidInputs_returnsProduct(int unitPrice, int quantity, long expected) {
    //     // Arrange
    //
    //     // Act
    //
    //     // Assert
    // }


    // -------------------------------------------------------------------------
    // TODO — Group B1: assertThrows for quantity = 0.
    // -------------------------------------------------------------------------

    // @Test
    // void total_withQuantityZero_throwsIllegalArgumentException() {
    //     // Arrange
    //
    //     // Act + Assert
    // }


    // -------------------------------------------------------------------------
    // TODO — Group B2: assertThrows for quantity = 1001.
    // -------------------------------------------------------------------------

    // @Test
    // void total_withQuantityAboveUpperBound_throwsIllegalArgumentException() {
    //     // Arrange
    //
    //     // Act + Assert
    // }
}
