package com.example.login;

import java.time.Clock;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * Validates login attempts against a small in-memory user store.
 *
 * Spec:
 *   - Username: 3-20 chars, alphanumeric and underscore only.
 *   - Password: 8+ chars, must contain at least one digit AND one special char.
 *   - Lockout: 3 consecutive failed attempts within 15 minutes -> account is locked.
 *
 * This class is the System Under Test for LoginValidatorTest. Do not modify it.
 */
public class LoginValidator {

    private static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z0-9_]+$");
    private static final Pattern PASSWORD_DIGIT = Pattern.compile(".*\\d.*");
    private static final Pattern PASSWORD_SPECIAL =
            Pattern.compile(".*[!@#$%^&*()_+\\-=\\[\\]{};:'\",.<>?/\\\\|].*");

    private static final int MIN_USERNAME_LENGTH = 3;
    private static final int MAX_USERNAME_LENGTH = 20;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final int LOCKOUT_THRESHOLD = 3;
    private static final Duration LOCKOUT_WINDOW = Duration.ofMinutes(15);

    private final Map<String, String> users;
    private final Map<String, FailureRecord> failures = new HashMap<>();
    private final Clock clock;

    public LoginValidator(Map<String, String> users) {
        this(users, Clock.systemDefaultZone());
    }

    public LoginValidator(Map<String, String> users, Clock clock) {
        this.users = new HashMap<>(users);
        this.clock = clock;
    }

    /**
     * Validates the (username, password) pair.
     *
     * @throws ValidationException          if either input fails the format rules
     * @throws AccountLockedException       if the account is currently locked
     * @throws InvalidCredentialsException  if the password is wrong for this user
     */
    public void validate(String username, String password) {
        validateUsernameFormat(username);
        validatePasswordFormat(password);

        if (isLocked(username)) {
            throw new AccountLockedException("Account '" + username + "' is locked.");
        }

        String expected = users.get(username);
        if (expected == null || !expected.equals(password)) {
            recordFailure(username);
            throw new InvalidCredentialsException("Invalid username or password.");
        }

        // Successful login resets the failure counter for this user.
        failures.remove(username);
    }

    // ---------- internals ----------

    private void validateUsernameFormat(String username) {
        if (username == null
                || username.length() < MIN_USERNAME_LENGTH
                || username.length() > MAX_USERNAME_LENGTH) {
            throw new ValidationException("Username must be 3-20 characters.");
        }
        if (!USERNAME_PATTERN.matcher(username).matches()) {
            throw new ValidationException("Username must be alphanumeric and underscore only.");
        }
    }

    private void validatePasswordFormat(String password) {
        if (password == null || password.length() < MIN_PASSWORD_LENGTH) {
            throw new ValidationException("Password must be at least 8 characters.");
        }
        if (!PASSWORD_DIGIT.matcher(password).matches()) {
            throw new ValidationException("Password must contain at least one digit.");
        }
        if (!PASSWORD_SPECIAL.matcher(password).matches()) {
            throw new ValidationException("Password must contain at least one special character.");
        }
    }

    private boolean isLocked(String username) {
        FailureRecord r = failures.get(username);
        if (r == null) return false;
        Duration sinceFirst = Duration.between(r.firstFailure, clock.instant());
        return r.count >= LOCKOUT_THRESHOLD && sinceFirst.compareTo(LOCKOUT_WINDOW) <= 0;
    }

    private void recordFailure(String username) {
        Instant now = clock.instant();
        FailureRecord r = failures.get(username);
        if (r == null || Duration.between(r.firstFailure, now).compareTo(LOCKOUT_WINDOW) > 0) {
            failures.put(username, new FailureRecord(now, 1));
        } else {
            failures.put(username, new FailureRecord(r.firstFailure, r.count + 1));
        }
    }

    private static final class FailureRecord {
        final Instant firstFailure;
        final int count;
        FailureRecord(Instant firstFailure, int count) {
            this.firstFailure = firstFailure;
            this.count = count;
        }
    }
}
