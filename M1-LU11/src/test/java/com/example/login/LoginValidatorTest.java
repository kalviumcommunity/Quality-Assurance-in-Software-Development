package com.example.login;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Your job: implement exactly FOUR test executions for LoginValidator.
 *
 * Required scope (from question.md):
 *   (a) 1 valid case                  -> credentials accepted, no exception
 *   (b1) BVA: username of length 2    -> ValidationException
 *   (b2) EP : password missing special-> ValidationException
 *   (c) Lockout after 3 failed tries  -> AccountLockedException
 *
 * Hard requirements:
 *   - Cases (a), (b1), (b2) MUST be implemented as a single @ParameterizedTest
 *     with @CsvSource (one row per case).
 *   - Case (c) MUST be a separate @Test method.
 *   - Naming convention: methodName_condition_expectedBehaviour
 *     (example: validateLogin_withUsernameOfTwoChars_throwsValidationException)
 *   - Every method shows AAA structure (Arrange / Act / Assert),
 *     either with comments or with clear blank-line separation.
 *   - `mvn test` must print: Tests run: 4, Failures: 0, Errors: 0
 *
 * Tip — the production class accepts a Map<String,String> of (username -> correct password)
 *       in its constructor, so you can inject a known user for the test.
 */
class LoginValidatorTest {

    // ---- A small known user store you can reuse across the tests -----------
    private static final Map<String, String> USER_STORE = Map.of("alice_99", "Valid123!");

    // -------------------------------------------------------------------------
    // TODO (a + b1 + b2): one @ParameterizedTest method, three CSV rows.
    //
    // Hint: a single CSV column for the expected outcome ("NONE" vs an
    // exception class name) lets one method handle both the valid and the
    // invalid cases without branching messily.
    // -------------------------------------------------------------------------

    // @ParameterizedTest(name = "...")
    // @CsvSource({
    //     "...",
    //     "...",
    //     "..."
    // })
    // void validateLogin_..._...(...) {
    //     // Arrange
    //
    //     // Act + Assert
    // }


    // -------------------------------------------------------------------------
    // TODO (c): one @Test method for the lockout scenario.
    //
    // Steps:
    //   1) Build a LoginValidator with USER_STORE.
    //   2) Call validate() three times with the WRONG password —
    //      each call should throw InvalidCredentialsException.
    //   3) The fourth call, even with the CORRECT password, should throw
    //      AccountLockedException.
    // -------------------------------------------------------------------------

    // @Test
    // void validateLogin_afterThreeFailedAttempts_locksAccount() {
    //     // Arrange
    //
    //     // Act
    //
    //     // Assert
    // }
}
