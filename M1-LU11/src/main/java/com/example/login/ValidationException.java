package com.example.login;

/** Thrown when username or password does not satisfy the format rules. */
public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
