# LU 2.5 PR Assignment — Starter Repository

## What this is

A Maven starter project for the LU 2.5 JUnit PR assignment. The production class `PriceCalculator.java` is already implemented. Your only job is to write `PriceCalculatorTest.java`.

## Project structure

```
src/
  main/java/com/example/pricing/
    PriceCalculator.java          ← DO NOT MODIFY
  test/java/com/example/pricing/
    PriceCalculatorTest.java      ← YOUR FILE — implement this
pom.xml                           ← DO NOT MODIFY dependencies
```

## The specification

`PriceCalculator.total(int unitPrice, int quantity)`:
- `unitPrice` — non-negative integer, 0 to 10,000,000 inclusive.
- `quantity` — integer from 1 to 1,000 inclusive.
- Returns `unitPrice × quantity` as a `long`.
- Throws `IllegalArgumentException` when either input is outside its range.

## How to run the tests locally

```bash
mvn test
```

Before you implement anything the project compiles and Surefire reports:

```
Tests run: 0, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

After you finish implementing all six test executions it should print:

```
Tests run: 6, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

## Submission

See `question.md` in the PR assignment folder for full submission instructions.
