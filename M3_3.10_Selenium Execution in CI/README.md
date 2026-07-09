# M3_3.10 — Selenium Execution in CI (Starter)

A working Selenium + JUnit 5 login test that you must wire into a **GitHub Actions** CI pipeline.

## What is already provided (taught in LU 3.10)

- `BaseTest.java` — **headless ChromeOptions** config (`--headless=new`, `--no-sandbox`, `--disable-dev-shm-usage`). Do not modify.
- `LoginPageTest.java` — a passing Sauce Demo login test. Do not modify.
- `pom.xml` — Selenium, JUnit 5, and Surefire already wired in.
- `.github/workflows/ci.yml` — a workflow with **Checkout**, **Set up JDK**, and **Build** steps already written.

## Your task (the remaining half)

Edit `.github/workflows/ci.yml` and add exactly two steps after `Build`:

1. A `Test` step that runs the suite headless:
   ```yaml
   - name: Test
     run: mvn -B test
   ```
2. An upload step that captures the Surefire report **even when tests fail**:
   ```yaml
   - name: Upload Surefire report
     if: always()
     uses: actions/upload-artifact@v4
     with:
       name: surefire-reports
       path: target/surefire-reports/**
   ```

Push to your fork, let the workflow run, confirm the job is green, and submit a PDF with:
- a screenshot of the green run (Checkout, Build, **Test** all green), and
- a screenshot of the run summary showing the `surefire-reports` artifact.

## Run locally first

```bash
mvn -B test
```

You should see `BUILD SUCCESS` with the login test passing before you wire up CI.

> `ubuntu-latest` ships Chrome, and Selenium Manager resolves the matching driver
> automatically — no extra browser setup step is needed in the workflow.
