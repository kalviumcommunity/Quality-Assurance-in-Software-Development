package com.example.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Base test class — PROVIDED. Do not modify for the assignment.
 *
 * The headless Chrome configuration below is what makes the browser start
 * inside a CI container that has no display. It was taught in Scene 2 of LU 3.10.
 */
public abstract class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments(
            "--headless=new",          // run Chrome with no visible window
            "--no-sandbox",            // required inside most CI containers
            "--disable-dev-shm-usage"  // avoid /dev/shm crashes on small runners
        );
        // Selenium Manager downloads the matching driver automatically.
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
