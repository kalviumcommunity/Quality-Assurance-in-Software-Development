package com.example.tests;

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
 * LU 3.7 - STARTER (flat) test. Your job is to extract a Page Object Model framework.
 *
 * This one method mixes three responsibilities that should each have their own home:
 *   1. Driver lifecycle (create + quit)  -> move to a BaseTest class (@BeforeEach / @AfterEach).
 *   2. Locators + actions (the By selectors and findElement/click calls)
 *                                          -> move to a LoginPage class:
 *                                             3 private By locators (username, password, submit)
 *                                             + 2 public actions (enterCredentials, clickLogin),
 *                                             with NO assertions in the page.
 *   3. Assertion                          -> stays in the test.
 *
 * When you are done:
 *   - pages/LoginPage.java holds the locators (private) and actions (public), no assertions.
 *   - tests/BaseTest.java owns the driver lifecycle; the refactored test `extends BaseTest`.
 *   - the test creates no driver of its own and calls LoginPage actions instead of findElement.
 *
 * OUT OF SCOPE (do not do): parallel execution, ThreadLocal, multi-page POM.
 *
 * Sauce Demo data-test attributes: [data-test='username'], [data-test='password'],
 * [data-test='login-button']; success is the /inventory.html URL.
 */
class FlatLoginTest {

    @Test
    void login_landsOnInventory() {
        ChromeOptions options = new ChromeOptions();
        if (System.getenv("CI") != null || Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new", "--no-sandbox",
                    "--disable-dev-shm-usage", "--window-size=1280,1024");
        }
        WebDriver driver = new ChromeDriver(options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        driver.get("https://www.saucedemo.com/");
        driver.findElement(By.cssSelector("[data-test='username']")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("[data-test='password']")).sendKeys("secret_sauce");
        wait.until(ExpectedConditions.elementToBeClickable(
                By.cssSelector("[data-test='login-button']"))).click();

        wait.until(ExpectedConditions.urlContains("/inventory.html"));
        assertTrue(driver.getCurrentUrl().contains("/inventory.html"));

        driver.quit();
    }
}
