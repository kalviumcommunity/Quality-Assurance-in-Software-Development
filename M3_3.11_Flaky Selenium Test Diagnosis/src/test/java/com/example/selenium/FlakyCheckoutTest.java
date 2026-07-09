package com.example.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * FLAKY TEST — this is the one you must fix.
 *
 * It logs in, then clicks the cart / checkout element IMMEDIATELY after the page
 * transitions. On fast machines the element is ready in time; on slow runs the
 * test acts before the element is interactive and throws NoSuchElementException
 * on an element that is actually present. See FAILURE_TRACE.txt.
 *
 * YOUR TASK:
 *   1. Read FAILURE_TRACE.txt and classify the root cause (Scene 6 of LU 3.11).
 *   2. Apply the matching fix using the provided `wait` helper from BaseTest.
 *      (For a timing race: wait.until(ExpectedConditions.elementToBeClickable(...)).)
 *   3. Run the test twice in a row and confirm it is green both times.
 *
 * Do NOT "fix" this with Thread.sleep, @Disabled, or by removing the assertion.
 */
class FlakyCheckoutTest extends BaseTest {

    @Test
    void login_thenOpenCart_reachesCartPage() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // FLAKY LINE: acts immediately after the login transition, before the
        // cart link is guaranteed to be interactive on slow runs.
        driver.findElement(By.className("shopping_cart_link")).click();

        assertTrue(driver.getCurrentUrl().contains("/cart.html"),
                "Clicking the cart should open the cart page");
    }
}
