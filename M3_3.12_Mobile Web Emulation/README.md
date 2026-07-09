# M3_3.12 — Mobile Testing on Your Laptop (Starter)

The lesson covered full native automation with **Appium** and **Espresso** (which need an Android emulator/device). This assignment proves the **same mobile-testing loop on your laptop only** — using Chrome **mobile emulation** through plain Selenium. No Android SDK, no emulator, no Appium server, no APK.

## Why this is still "mobile testing"

Chrome mobile emulation gives you a real phone-sized viewport, a mobile user-agent, and touch events — the same surface Appium drives when it tests **mobile web**. You practise the find → act → assert loop and mobile-viewport thinking without a device farm.

## What is already provided

- `MobileEmulationLoginTest.java` — a Selenium test that logs into `saucedemo.com` in a phone viewport, asserts the logged-in page, asserts the viewport is phone-sized, and saves `target/mobile-login.png`.
- `pom.xml` — Selenium 4.20 + JUnit 5. **Selenium Manager auto-downloads the driver**, so nothing else to install but Chrome + JDK 17 + Maven.

## Prerequisites (laptop only)

- **JDK 17** and **Maven**
- **Google Chrome** installed (any recent version)

## Your task (the remaining half)

1. In `setUp()`, complete the **`mobileEmulation` device profile** (the block taught in the LU is stubbed with a `TODO`).
2. Run it:
   ```bash
   mvn test
   ```
3. Confirm the test is **green**, and open the saved **`target/mobile-login.png`** — it shows the site rendered at phone width.

## Submit

One screenshot (or PDF) showing:
- **(A)** the test passing — `login_onMobileViewport_reachesInventory` green (`Tests run: 1, Failures: 0 … BUILD SUCCESS`), and
- **(B)** the mobile render — `target/mobile-login.png` (the site at phone width),
plus a one-line caption naming the emulated device profile and what you asserted (logged-in page + phone-sized viewport).
