package com.carsharing.exception;

public class EmptyCarInventoryException extends RuntimeException {
    public EmptyCarInventoryException(String message) {
        super(message);
    }
}
