package com.carsharing.exception;

public class InvalidJwtAuthenticationException extends RuntimeException {
    public InvalidJwtAuthenticationException(String message, Exception cause) {
        super(message, cause);
    }
}
