package com.carsharing.exception;

public class NoSuchCarIdException extends RuntimeException {
    public NoSuchCarIdException(String message) {
        super(message);
    }
}
