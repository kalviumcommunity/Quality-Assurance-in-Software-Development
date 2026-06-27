# LU 3.5 - Stable Selenium Tests (Starter Repo)

Refactor a flaky Selenium test into a reliable one, against the **Sauce Demo** practice app.

- Application under test: https://www.saucedemo.com/
- Valid credentials: `standard_user` / `secret_sauce`
- A successful login redirects to `/inventory.html` and shows a `Products` heading.

## Prerequisites

- JDK 17 or later
- Maven 3.8 or later
- Google Chrome (or Edge) installed. Selenium Manager downloads the matching driver automatically.
- An internet connection (the test navigates to a live URL).

## What to do

Open `src/test/java/com/example/selenium/FlakyLoginTest.java`. It runs but is unreliable. Refactor it:

1. **Remove every `Thread.sleep`** and replace it with an explicit `WebDriverWait` keyed to the correct `ExpectedConditions`:
   - `elementToBeClickable(...)` before a click,
   - `visibilityOfElementLocated(...)` before reading text,
   - `urlContains(...)` after a navigation.
2. **Replace every positional locator** with a stable data-attribute locator. Sauce Demo exposes `data-test` attributes (see the table below). No positional XPath or `nth-child` chain may remain.
3. Keep an assertion that proves the success state.
4. Run the refactored test and take a screenshot of the passing run.

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

Take **one screenshot** that clearly shows your refactored test (no `Thread.sleep`, no positional XPath) and the passing result, then upload it on the course portal.

## Locator reference (Sauce Demo, data-test attributes)

| Element | Stable locator |
|---|---|
| Username field | `By.cssSelector("[data-test='username']")` |
| Password field | `By.cssSelector("[data-test='password']")` |
| Login button | `By.cssSelector("[data-test='login-button']")` |
| Page heading ("Products") | `By.cssSelector("[data-test='title']")` |
