package com.example.mobileweb;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Laptop-only mobile testing — no Android SDK, emulator, or Appium needed.
 *
 * This drives Chrome in MOBILE EMULATION: a phone-sized viewport, a mobile
 * user-agent, and touch events. It is the same find-act-assert loop you use
 * for native automation (Appium/Espresso), on the one mobile surface a plain
 * laptop can run today: a mobile web view. Selenium 4 auto-manages the driver.
 *
 * YOUR TASK (the remaining half — the mobileEmulation block was taught in the LU):
 *   1. Fill in the mobileEmulation device profile in setUp() (marked TODO).
 *   2. Run the test and confirm it is green.
 *   3. It saves target/mobile-login.png — use it as your visual proof.
 */
class MobileEmulationLoginTest {

    private WebDriver driver;

    @BeforeEach
    void setUp() {
        // A phone profile: 393x851 @ 2.75 dpr with a Pixel mobile user-agent.
        Map<String, Object> deviceMetrics = new HashMap<>();
        deviceMetrics.put("width", 393);
        deviceMetrics.put("height", 851);
        deviceMetrics.put("pixelRatio", 2.75);

        Map<String, Object> mobileEmulation = new HashMap<>();
        // TODO: attach the phone profile taught in the LU:
        mobileEmulation.put("deviceMetrics", deviceMetrics);
        mobileEmulation.put("userAgent",
                "Mozilla/5.0 (Linux; Android 14; Pixel 7) AppleWebKit/537.36 "
              + "(KHTML, like Gecko) Chrome/124.0.0.0 Mobile Safari/537.36");

        ChromeOptions options = new ChromeOptions();
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        // options.addArguments("--headless=new"); // optional; screenshots still work

        driver = new ChromeDriver(options);
    }

    @Test
    void login_onMobileViewport_reachesInventory() throws Exception {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // assert the logged-in state, same as any UI test
        assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "Login on the mobile viewport should reach the inventory page");

        // assert we really are on a phone-sized (mobile) viewport, not desktop
        long innerWidth = (Long) ((JavascriptExecutor) driver)
                .executeScript("return window.innerWidth;");
        assertTrue(innerWidth < 500,
                "Viewport should be phone-sized; was " + innerWidth + "px");

        System.out.println("[mobile] reached " + driver.getCurrentUrl()
                + " | viewport innerWidth = " + innerWidth + "px");

        // save visual proof of the mobile render
        File shot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        new File("target").mkdirs();
        Files.copy(shot.toPath(), new File("target/mobile-login.png").toPath(),
                java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
