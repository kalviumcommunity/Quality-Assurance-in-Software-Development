# M3_3.7_Scalable Selenium Framework Design

This is the **student starter** project for the LU 3.7 assignment. It contains one **flat**
Selenium login test that you must refactor into the beginning of a **Page Object Model** framework.

Application under test: **Sauce Demo** (`https://www.saucedemo.com/`), credentials
`standard_user` / `secret_sauce`. A successful login redirects to `/inventory.html`. It exposes
`data-test` attributes: `username`, `password`, `login-button`.

## Prerequisites

- JDK 17 or later
- Maven 3.8 or later
- Google Chrome (or Edge) installed. Selenium Manager downloads the matching driver automatically.
- An internet connection (the test navigates to a live URL).

## The test you start from

`src/test/java/com/example/tests/FlatLoginTest.java` runs, but it is **flat**: one method creates
the driver, holds the locators, calls `findElement` directly, asserts, and quits. Three
responsibilities are tangled together.

## What to build

Extract a small Page Object Model framework so that:

1. **`pages/LoginPage.java`** has **3 private `By` locators** (username, password, submit) and **2 public action methods** (`enterCredentials(String user, String pass)`, `clickLogin()`). **No assertions in the page.**
2. **`tests/BaseTest.java`** owns the driver lifecycle: `@BeforeEach` creates `driver` and `wait`; `@AfterEach` quits.
3. The **refactored test** `extends BaseTest`, constructs `LoginPage` with the inherited `driver`/`wait`, and uses its actions **instead of direct `findElement` calls**. It creates no driver of its own and keeps a success-only assertion (`/inventory.html`).
4. Keep stable `data-test` locators and explicit `WebDriverWait`. **No `Thread.sleep`.**

> **Out of scope (do not do):** parallel execution, `ThreadLocal<WebDriver>`, and multi-page POM. Keep this framework to one page, one base class, one test.

## Target structure

```
src/test/java/com/example/
  pages/LoginPage.java     locators (private) + actions (public), no assertions
  tests/BaseTest.java      @BeforeEach driver init, @AfterEach quit
  tests/LoginTest.java     extends BaseTest, uses LoginPage, asserts
pom.xml
```

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

1. Fork this repository, create a branch (e.g. `feat/pom-framework`), add `pages/LoginPage.java` and `tests/BaseTest.java`, and refactor the test.
2. Push the branch and open a Pull Request into your fork's `main`.
3. Wait for the **`Tests / mvn test`** GitHub Actions check to finish green.
4. Submit the PR link on the course portal once the check is green.

In the PR description, name what moved where (locators + actions to `LoginPage`, lifecycle to `BaseTest`, assertion stayed in the test) plus one line of risk rationale (QA risk reduced and one limitation that remains).

## Locator reference (Sauce Demo, data-test attributes)

| Element | Stable locator |
|---|---|
| Username field | `By.cssSelector("[data-test='username']")` |
| Password field | `By.cssSelector("[data-test='password']")` |
| Login button | `By.cssSelector("[data-test='login-button']")` |

## Guidelines

- Do not add new dependencies to `pom.xml`. Selenium and JUnit 5 are already wired in.
- No assertions may appear in `LoginPage`. No `Thread.sleep` anywhere.
- The refactored test must not create its own `WebDriver`; it inherits `driver`/`wait` from `BaseTest`.
- Selenium Manager downloads the browser driver automatically; do not commit a driver binary.
