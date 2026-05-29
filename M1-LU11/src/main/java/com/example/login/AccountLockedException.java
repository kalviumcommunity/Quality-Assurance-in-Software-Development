package com.example.login;

/** Thrown when validate() is called on an account that has been locked. */
public class AccountLockedException extends RuntimeException {
    public AccountLockedException(String message) {
        super(message);
    }
}
