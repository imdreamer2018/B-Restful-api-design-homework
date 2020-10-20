package com.thoughtworks.capability.gtb.restfulapidesign.exception;

public class BadRequestException extends RuntimeException {
    public BadRequestException(String message) {
        super(message);
    }
}
