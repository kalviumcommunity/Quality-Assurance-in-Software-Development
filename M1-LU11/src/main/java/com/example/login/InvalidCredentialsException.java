package com.example.login;

/** Thrown when the password does not match the stored password for the given username. */
public class InvalidCredentialsException extends RuntimeException {
    public InvalidCredentialsException(String message) {
        super(message);
    }
}
