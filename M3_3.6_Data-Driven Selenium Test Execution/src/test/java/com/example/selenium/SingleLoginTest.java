package com.example.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * LU 3.6 - STARTER (single credential) test. Your job is to make it data-driven.
 *
 * Right now this test covers exactly one credential: the valid user. Convert it
 * into ONE @ParameterizedTest driven by a CSV of five credential rows:
 *   1. standard_user   / secret_sauce    -> SUCCESS (lands on /inventory.html, "Products")
 *   2. standard_user   / wrong_password  -> ERROR   ("do not match")
 *   3. locked_out_user / secret_sauce    -> ERROR   ("locked out")
 *   4. (empty)         / secret_sauce    -> ERROR   ("Username is required")  [quote the empty CSV cell as ""]
 *   5. ' OR '1'='1     / ' OR '1'='1      -> ERROR   ("do not match", injection safely rejected)
 *
 * Steps:
 *   - Add src/test/resources/credentials.csv with a header and the five rows above,
 *     including an outcome column and a message-fragment column.
 *   - Replace @Test with @ParameterizedTest + @CsvFileSource(resources = "/credentials.csv",
 *     numLinesToSkip = 1), taking the CSV columns as method parameters.
 *   - Branch the assertion on the outcome column: for SUCCESS assert the /inventory.html URL
 *     and [data-test='title'] contains "Products"; for ERROR assert [data-test='error'] text
 *     contains the fragment and that /inventory.html was NOT reached.
 *
 * Keep the stable data-test locators and explicit waits below (no Thread.sleep).
 *
 * Sauce Demo data-test attributes: [data-test='username'], [data-test='password'],
 * [data-test='login-button'], [data-test='title'] (inventory heading),
 * [data-test='error'] (the login error banner).
 */
class SingleLoginTest {

    private static final String LOGIN_URL = "https://www.saucedemo.com/";

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (System.getenv("CI") != null || Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new", "--no-sandbox",
                    "--disable-dev-shm-usage", "--window-size=1280,1024");
        }
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void login_withValidCredentials_landsOnInventory() {
        driver.get(LOGIN_URL);

        driver.findElement(By.cssSelector("[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("[data-test='password']")).sendKeys("secret_sauce");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-test='login-button']"))).click();

        wait.until(ExpectedConditions.urlContains("/inventory.html"));
        assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
    }
}
