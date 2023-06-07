package com.carsharing.exception;

public class NoSuchRentalIdException extends RuntimeException {
    public NoSuchRentalIdException(String message) {
        super(message);
    }
}
