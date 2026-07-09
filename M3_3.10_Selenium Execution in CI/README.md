# M3_3.10 — Selenium Execution in CI (Starter)

A working Selenium + JUnit 5 login test that you must wire into a CI pipeline.

## What is already provided (taught in LU 3.10)

- `BaseTest.java` — **headless ChromeOptions** config (`--headless=new`, `--no-sandbox`, `--disable-dev-shm-usage`). Do not modify.
- `LoginPageTest.java` — a passing Sauce Demo login test. Do not modify.
- `pom.xml` — Selenium, JUnit 5, and Surefire already wired in.
- `Jenkinsfile` — **Checkout** and **Build** stages already written.

## Your task (the remaining half)

Edit `Jenkinsfile`:

1. Add a `stage('Test')` that runs `sh 'mvn -B test'`.
2. Add a `post { always { ... } }` block that runs:
   ```groovy
   junit 'target/surefire-reports/*.xml'
   archiveArtifacts artifacts: 'target/surefire-reports/**', allowEmptyArchive: false
   ```

Run the pipeline, confirm all stages are green, and submit a PDF with:
- a screenshot of the green pipeline (Checkout, Build, Test), and
- a screenshot of the archived `surefire-reports` artefact / Test Result summary.

## Run locally first

```bash
mvn -B test
```

You should see `BUILD SUCCESS` with the login test passing before you wire up CI.
