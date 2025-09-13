package com.stayndine.customers.infrastructure.in.error;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> notFound(ResourceNotFoundException ex) {
        return ResponseEntity.status(404).body(Map.of("status", 404, "error", "not_found", "message", ex.getMessage()));
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, Object>> validation(ValidationException ex) {
        return ResponseEntity.badRequest().body(Map.of("status", 400, "error", "bad_request", "message", ex.getMessage()));
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<Map<String, Object>> forbidden(ForbiddenException ex) {
        return ResponseEntity.status(403).body(Map.of("status", 403, "error", "forbidden", "message", ex.getMessage()));
    }

}