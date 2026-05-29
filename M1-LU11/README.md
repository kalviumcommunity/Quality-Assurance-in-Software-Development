# Login Validator — JUnit Test Suite

This is the starter repository for the JUnit Login Test Suite assignment. The production code is already written. Your job is to write the tests.

---

## What This Project Is

You are working with a `LoginValidator` class — a small in-memory login system that validates a username and password against a set of rules and tracks failed attempts to lock out accounts.

The production code lives in `src/main/java/`. You will write tests in `src/test/java/`. You must not change any production code.

---

## Project Structure

```
login-validator/
├── .github/
│   └── workflows/
│       └── test.yml              ← CI workflow that runs mvn test on every PR
├── src/
│   ├── main/java/com/example/login/
│   │   ├── LoginValidator.java            ← the system under test (do not modify)
│   │   ├── ValidationException.java       ← thrown for bad input format
│   │   ├── InvalidCredentialsException.java  ← thrown for wrong password
│   │   └── AccountLockedException.java   ← thrown when account is locked
│   └── test/java/com/example/login/
│       └── LoginValidatorTest.java        ← your file to implement
├── pom.xml                       ← Maven build + JUnit 5 dependencies
└── README.md
```

---

## The Spec You Are Testing

`LoginValidator.validate(username, password)` enforces these rules:

| Rule | Detail |
|------|--------|
| Username length | 3 to 20 characters |
| Username characters | Alphanumeric and underscore only |
| Password length | 8 or more characters |
| Password composition | Must contain at least one digit AND one special character |
| Lockout | After 3 consecutive failed attempts within 15 minutes, the account is locked — even if the next password is correct |

**What the method throws:**
- `ValidationException` — if username or password fails the format rules
- `InvalidCredentialsException` — if the password is wrong (and format is valid)
- `AccountLockedException` — if the account is currently locked

---

## Prerequisites

Make sure both of these are installed before you start:

```bash
java --version    # must show 17 or later
mvn --version     # must show 3.6 or later
```

If either is missing, complete the QA Toolkit Setup (LU 1.1.1) first.

---

## How to Run the Tests

From the root of this project (the folder containing `pom.xml`):

```bash
mvn test
```

**What you will see before implementing the tests** (the skeleton compiles but runs nothing):

```
[INFO] Tests run: 0, Failures: 0, Errors: 0, Skipped: 0
[INFO] BUILD SUCCESS
```

**What you should see after implementing all four test cases correctly:**

```
[INFO] -------------------------------------------------------
[INFO]  T E S T S
[INFO] -------------------------------------------------------
[INFO] Running com.example.login.LoginValidatorTest
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 0.XXX s
[INFO]
[INFO] Results:
[INFO]
[INFO] Tests run: 4, Failures: 0, Errors: 0, Skipped: 0
[INFO]
[INFO] BUILD SUCCESS
```

> The parameterized test counts as 3 of those 4 runs even though you write it as a single method — one row per `@CsvSource` entry.

If you see `BUILD FAILURE`, scroll up to the `[ERROR]` lines. The test name in the failure message will tell you exactly which case broke and what was expected versus what actually happened.

---

## What You Need to Build

Open `src/test/java/com/example/login/LoginValidatorTest.java`. The file has a skeleton with TODO comments. You need to implement exactly **four test executions**:

| # | Case | Expected result |
|---|------|-----------------|
| a | Valid username and password | No exception thrown |
| b1 | Username with only 2 characters | `ValidationException` |
| b2 | Password that has no special character | `ValidationException` |
| c | Three wrong-password attempts, then one more attempt (even with correct password) | `AccountLockedException` |

**Hard requirements:**
- Cases a, b1, and b2 must be a **single `@ParameterizedTest`** method using `@CsvSource` — one row per case.
- Case c must be a **separate `@Test`** method.
- Every method name must follow the convention: `methodName_condition_expectedBehaviour`
- Every method must show AAA structure: Arrange, Act, Assert — either with comments or with blank-line separation.

---

## Hints

**Getting started — how to create a validator in your test:**

```java
// LoginValidator takes a Map of username -> correct password
Map<String, String> users = Map.of("alice_99", "Valid123!");
LoginValidator validator = new LoginValidator(users);
```

**Hint 1 — username boundary:**
The spec says username must be 3–20 characters. A username of length 2 is just below the minimum. What string of length 2 would you pass?

**Hint 2 — password missing special character:**
`"NoSpecial1"` is 10 characters and has a digit but no special character (`!@#$%^&*` etc). That's the invalid equivalence class you need.

**Hint 3 — the lockout test needs the right kind of wrong password:**
Your wrong password for the three failed attempts must still pass format validation — otherwise `ValidationException` is thrown instead of `InvalidCredentialsException` and the failure counter never increments. A password like `"Wrong123!"` works: it's 9 characters, has a digit and a special character, but it's not the correct password for the user.

**Hint 4 — asserting exceptions in JUnit 5:**
```java
// assert that calling validate throws a specific exception type
assertThrows(ValidationException.class, () -> validator.validate(username, password));

// assert that calling validate does NOT throw anything
assertDoesNotThrow(() -> validator.validate(username, password));
```

**Hint 5 — the lockout loop:**
You need to call `validate()` three times with a wrong password before the lockout kicks in. A simple `for` loop works. Each call in the loop should itself throw `InvalidCredentialsException` — you can assert that inside the loop too.

---

## Submitting

1. Fork this repository on your GitHub account.
2. Clone your fork and create a branch — for example `feature/login-validator-tests`.
3. Implement `LoginValidatorTest.java`.
4. Run `mvn test` locally and confirm `Tests run: 4, Failures: 0, Errors: 0`.
5. Push your branch and open a Pull Request into your fork's `main`.
6. Wait for the `Tests / mvn test` CI check on the PR to go green.
7. Submit the PR link on the course portal.
