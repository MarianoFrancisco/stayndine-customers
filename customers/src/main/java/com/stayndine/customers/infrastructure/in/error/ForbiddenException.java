package com.stayndine.customers.infrastructure.in.error;

public class ForbiddenException extends RuntimeException {
    public ForbiddenException(String m) {
        super(m);
    }
}
