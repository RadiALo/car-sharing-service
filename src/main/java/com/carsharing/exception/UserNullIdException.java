package com.carsharing.exception;

public class UserNullIdException extends RuntimeException {
    public UserNullIdException(String message) {
        super(message);
    }
}
