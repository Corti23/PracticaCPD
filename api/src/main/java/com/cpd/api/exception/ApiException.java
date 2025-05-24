package com.cpd.api.exception;

public class ApiException extends RuntimeException {

    public ApiException(String message) {
        super(message);
    }
}