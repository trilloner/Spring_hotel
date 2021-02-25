package com.example.hotel.exceptions;

/**
 * Custom exception class
 */
public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
