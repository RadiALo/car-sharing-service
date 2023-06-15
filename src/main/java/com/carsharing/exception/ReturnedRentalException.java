package com.carsharing.exception;

public class ReturnedRentalException extends RuntimeException {
    public ReturnedRentalException(String message) {
        super(message);
    }
}
