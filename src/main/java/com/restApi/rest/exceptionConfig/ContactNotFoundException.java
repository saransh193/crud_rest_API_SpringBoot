package com.restApi.rest.exceptionConfig;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String errorMessage) {
            super(errorMessage);
        }
}
