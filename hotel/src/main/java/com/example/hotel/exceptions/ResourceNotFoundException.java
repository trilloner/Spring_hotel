package com.example.hotel.exceptions;

public class ResourceNotFoundException extends Exception {
    public ResourceNotFoundException(String resource) {
        super(resource);
    }
}
