# LU 2.7 — Java Integration Test Suite Design (Starter)

## Your task

Complete `OrderProcessorIntegrationTest.java` in `src/test/java/com/example/order/`.

The production code is already written — do **not** modify any file under `src/main/java/`.

### The chain you are testing

```
OrderProcessor.placeOrder(order)
  → InventoryService.checkStock(productId, quantity)   // throws OutOfStockException if insufficient
  → PaymentService.authorise(orderId, amount)          // records authorisation
  → InventoryService.decrementStock(productId, quantity)
  → return OrderResult(orderId, confirmed=true)
```

### Required tests

1. **Happy-path** — use **real instances** of all three services. Assert that the returned `OrderResult.isConfirmed()` is `true`, that `PaymentService.wasAuthorised(result.getOrderId())` is `true`, and that stock decremented by the ordered quantity.

2. **Stub-driven negative** — override `InventoryService.checkStock` to always throw `OutOfStockException`. Assert the exception propagates **and** `PaymentService.getAuthorisationCount() == 0` (payment was never attempted).

3. **`@BeforeEach`** — reinitialise all three service instances before every test and seed `PRODUCT_ID` with `INITIAL_STOCK = 5` units.

4. **Class-level comment** — state one QA risk the suite reduces and one limitation that remains.

### Build and verify

```bash
mvn test
```

Both tests must pass (`Tests run: 2, Failures: 0`) and `BUILD SUCCESS` before you submit.

## Submission

1. Fork the starter repo on GitHub.
2. Create a branch, implement the two tests, run `mvn test` locally (must be green).
3. Open a PR into your fork's `main`.
4. Wait for the `Tests / mvn test` CI check to go green.
5. Submit the PR link via the course portal.

**PR description (required — 2 lines minimum):**
- The two service-boundary scenarios your tests cover.
- Confirmation that `mvn test` exits 0.
