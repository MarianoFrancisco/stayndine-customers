package com.stayndine.customers.infrastructure.in.rest.dto;

import java.time.LocalDate;
import java.time.Instant;

public record CustomerResponse(
        String id, String email, String firstName, String lastName,
        String phone, LocalDate birthDate, String preferencesJson,
        Instant createdAt, Instant updatedAt
) {
}
