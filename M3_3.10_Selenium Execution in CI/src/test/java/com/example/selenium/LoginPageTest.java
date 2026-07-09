package com.example.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * PROVIDED — a working Selenium login test against Sauce Demo.
 * You do not change this file. Your task is to make it run in CI (the Jenkinsfile Test stage).
 */
class LoginPageTest extends BaseTest {

    @Test
    void login_withValidCredentials_reachesInventory() {
        driver.get("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        // success-only assertion: the inventory URL only appears after a real login
        assertTrue(driver.getCurrentUrl().contains("/inventory.html"),
                "Login should redirect to the inventory page");
    }
}
