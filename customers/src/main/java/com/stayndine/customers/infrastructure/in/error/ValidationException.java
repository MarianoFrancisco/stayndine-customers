package com.stayndine.customers.infrastructure.in.error;

public class ValidationException extends RuntimeException {
    public ValidationException(String m) {
        super(m);
    }
}
