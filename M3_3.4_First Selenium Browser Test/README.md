# LU 3.4 - First Selenium Test (Starter Repo)

Write your first Selenium WebDriver test (with JUnit 5 as the runner) against the **Sauce Demo** practice app.

- Application under test: https://www.saucedemo.com/
- Valid credentials: `standard_user` / `secret_sauce`
- A successful login redirects to `/inventory.html` and shows a `Products` heading.

## Prerequisites

- JDK 17 or later
- Maven 3.8 or later
- Google Chrome (or Edge) installed. Selenium Manager downloads the matching driver automatically, so there is no driver path to configure.
- An internet connection (the test navigates to a live URL).

## What to do

1. Open `src/test/java/com/example/selenium/LoginPageTest.java`.
2. Complete the `login_withValidCredentials_landsOnInventory()` test so that it:
   - navigates to the login URL,
   - locates the username field, password field, and login button,
   - types the valid credentials and clicks login,
   - asserts a **success-only** state (URL contains `/inventory.html`, the `Products` heading, or the visible cart).
3. Run the test and confirm it passes.

## Run the tests

```bash
mvn test
```

A passing run prints:

```
Tests run: 1, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Submit

Take **one screenshot** that clearly shows your test method and the passing result, then upload it on the course portal.

## Locator reference (Sauce Demo)

| Element | Locator |
|---|---|
| Username field | `By.id("user-name")` |
| Password field | `By.id("password")` |
| Login button | `By.id("login-button")` |
| Page heading ("Products") | `By.cssSelector("[data-test='title']")` |
| Shopping cart | `By.id("shopping_cart_container")` |
