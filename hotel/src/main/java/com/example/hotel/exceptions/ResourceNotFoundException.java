package com.example.hotel.exceptions;

/**
 * Custom exception class
 */
public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String resource) {
        super(resource);
    }
}
