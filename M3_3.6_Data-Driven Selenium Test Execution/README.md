# M3_3.6_Data Driven Selenium Test Execution

This is the **student starter** project for the LU 3.6 assignment. It contains one
**single-credential** Selenium login test that you must convert into a **data-driven**
test covering five credential combinations.

Application under test: **Sauce Demo** (`https://www.saucedemo.com/`). `data-test` hooks:
`username`, `password`, `login-button`, `title` (the "Products" heading on the inventory
page), and `error` (the login error banner). A successful login redirects to `/inventory.html`.

## Prerequisites

- JDK 17 or later
- Maven 3.8 or later
- Google Chrome (or Edge) installed. Selenium Manager downloads the matching driver automatically.
- An internet connection (the test navigates to a live URL).

## The test you start from

`src/test/java/com/example/selenium/SingleLoginTest.java` passes, but it covers only the
valid credential. Its locators and waits are already stable (from LU 3.5) - do not change
those; change **how many cases** it covers.

## What to do

Convert the test into one data-driven `@ParameterizedTest` (you may rename the class, e.g. to
`DataDrivenLoginTest`) so that:

1. Add **`src/test/resources/credentials.csv`** with a header and **five rows**, each with a
   username, password, an **outcome** column (`SUCCESS`/`ERROR`), and a **message** fragment:

   ```
   username,password,outcome,message
   standard_user,secret_sauce,SUCCESS,Products
   standard_user,wrong_password,ERROR,do not match
   locked_out_user,secret_sauce,ERROR,locked out
   "",secret_sauce,ERROR,Username is required
   ' OR '1'='1,' OR '1'='1,ERROR,do not match
   ```

   The empty-username cell is **quoted** (`""`) so it is an empty string, not `null`.

2. Replace `@Test` with **`@ParameterizedTest`** driven by
   `@CsvFileSource(resources = "/credentials.csv", numLinesToSkip = 1)`, taking the CSV
   columns as method parameters.

3. **Branch the assertion** on the outcome column:
   - For `SUCCESS`: wait on `urlContains("/inventory.html")` and assert `[data-test='title']` contains the message ("Products").
   - For `ERROR`: assert `[data-test='error']` text contains the message, and that `/inventory.html` was **not** reached.

4. Keep the stable `data-test` locators and explicit `WebDriverWait` conditions. **No `Thread.sleep`.**

## Run the tests

```bash
mvn test
```

A passing run prints (five parameterised invocations):

```
Tests run: 5, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Submit

1. Fork this repository, create a branch (e.g. `feat/data-driven-login`), add `credentials.csv`, and convert the test.
2. Push the branch and open a Pull Request into your fork's `main`.
3. Wait for the **`Tests / mvn test`** GitHub Actions check to finish green.
4. Submit the PR link on the course portal once the check is green.

In the PR description, **list the five credential cases** and name one QA risk the data-driven suite reduces plus one limitation that remains.

## Guidelines

- Do not add new dependencies to `pom.xml`. Selenium and JUnit 5 (including `junit-jupiter-params`) are already wired in.
- The `credentials.csv` file must live under `src/test/resources` and be read via `resources = "/credentials.csv"` (classpath, leading slash).
- No `Thread.sleep` and no positional XPath may remain anywhere in the test.
- Selenium Manager downloads the browser driver automatically; do not commit a driver binary.
