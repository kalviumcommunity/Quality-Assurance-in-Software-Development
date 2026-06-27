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
 * LU 3.5 - Stabilise this flaky Selenium test, then open a PR.
 *
 * Application under test: Sauce Demo (https://www.saucedemo.com/)
 * Valid credentials:      standard_user / secret_sauce
 *
 * This test is intentionally UNRELIABLE. Two habits poison it:
 *   1. Fixed sleeps (Thread.sleep) that guess at timing.
 *   2. A positional XPath locator that breaks on any layout change.
 *
 * Your task:
 *   - Replace EVERY Thread.sleep with an explicit WebDriverWait keyed to the
 *     correct ExpectedConditions (elementToBeClickable before a click,
 *     visibilityOfElementLocated before reading text, urlContains after a nav).
 *   - Replace EVERY positional locator with a stable data-attribute locator.
 *     Sauce Demo exposes data-test attributes:
 *        username     -> By.cssSelector("[data-test='username']")
 *        password     -> By.cssSelector("[data-test='password']")
 *        login button -> By.cssSelector("[data-test='login-button']")
 *        page heading -> By.cssSelector("[data-test='title']")  (text "Products")
 *   - Keep an assertion that proves the success state.
 *   - Confirm the CI check passes on your PR.
 */
class FlakyLoginTest {

    private static final String LOGIN_URL = "https://www.saucedemo.com/";

    WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        if (System.getenv("CI") != null || Boolean.getBoolean("headless")) {
            options.addArguments("--headless=new", "--no-sandbox",
                    "--disable-dev-shm-usage", "--window-size=1280,1024");
        }
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void login() throws InterruptedException {
        driver.get(LOGIN_URL);

        driver.findElement(By.name("user-name")).sendKeys("standard_user");
        driver.findElement(By.name("password")).sendKeys("secret_sauce");

        // BRITTLE: a fixed pause that is too long on fast runs and too short on slow ones.
        Thread.sleep(2000);

        // BRITTLE: a positional XPath (the 3rd input on the page) that snaps if
        // any field is added or reordered.
        driver.findElement(By.xpath("(//input)[3]")).click();

        // BRITTLE: another fixed pause.
        Thread.sleep(3000);

        assertTrue(driver.getCurrentUrl().contains("/inventory.html"));
    }
}
