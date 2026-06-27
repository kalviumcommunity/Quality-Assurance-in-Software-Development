package com.example.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * LU 3.4 - Your first Selenium WebDriver test.
 *
 * Application under test: Sauce Demo (https://www.saucedemo.com/)
 * Valid credentials:      standard_user / secret_sauce
 *
 * Your task (small scope):
 *   1. @BeforeEach launches the browser (provided) and navigates to the login URL.
 *   2. login_withValidCredentials_landsOnInventory():
 *        - locate the username field, password field, and login button BY ID,
 *        - type the valid credentials and click login,
 *        - assert a SUCCESS-ONLY state (the URL contains "/inventory.html",
 *          the page heading reads "Products", or the cart is visible).
 *      No Thread.sleep. No positional XPath.
 *   3. @AfterEach quits the driver (provided).
 *
 * Locators (Sauce Demo):
 *   username field -> By.id("user-name")
 *   password field -> By.id("password")
 *   login button   -> By.id("login-button")
 *   page heading   -> By.cssSelector("[data-test='title']")  (text "Products")
 *   shopping cart  -> By.id("shopping_cart_container")
 */
class LoginPageTest {

    private static final String LOGIN_URL = "https://www.saucedemo.com/";

    WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        // Run headless on CI (GitHub Actions sets CI=true); a visible browser locally.
        if (System.getenv("CI") != null || Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new", "--no-sandbox",
                    "--disable-dev-shm-usage", "--window-size=1280,1024");
        }
        // Selenium Manager downloads the matching driver automatically (Selenium 4.6+).
        driver = new ChromeDriver(options);
        driver.get(LOGIN_URL);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void login_withValidCredentials_landsOnInventory() {
        // TODO: Act - locate the fields and the button BY ID, type the credentials, click login.
        //   driver.findElement(By.id("user-name")).sendKeys("standard_user");
        //   ...

        // TODO: Assert - replace this placeholder with a real success-only assertion.
        //   Until you implement the login above, this fails cleanly: on the login
        //   page the URL is still https://www.saucedemo.com/, not /inventory.html.
        assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
    }
}
