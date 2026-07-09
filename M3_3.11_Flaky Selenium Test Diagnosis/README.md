# M3_3.11 — Flaky Selenium Test Diagnosis (Starter)

One intermittently-failing Selenium test that you must diagnose and fix.

## What is already provided (taught in LU 3.11)

- `BaseTest.java` — headless Chrome config **and a `WebDriverWait` helper (`wait`)** ready to use.
- `FlakyCheckoutTest.java` — the flaky test to fix.
- `FAILURE_TRACE.txt` — a representative stack trace from a failing run.
- `pom.xml` — Selenium + JUnit 5 + Surefire wired in.

## Your task (the remaining half)

1. Read `FAILURE_TRACE.txt` and **classify** the root cause using the five-cause mapping from the LU.
2. Apply the **matching fix** in `FlakyCheckoutTest.java` using the provided `wait` helper, e.g. for a timing race:
   ```java
   wait.until(ExpectedConditions.elementToBeClickable(By.className("shopping_cart_link"))).click();
   ```
3. Run the test **twice in a row** and confirm both runs are green.

Do **not** fix it with `Thread.sleep`, `@Disabled`, a retry loop, or by deleting the assertion — those score zero.

## Run it

```bash
mvn -B test            # run once
mvn -B test            # run again — confirm green both times
```

## Submit

A PDF with: (A) two consecutive green runs (test name visible), (B) the fix diff, and a three-line caption naming the exception, the cause, and the fix.
