package com.stayndine.customers.domain.model;

import java.time.LocalDate;
import java.time.Instant;
import java.util.UUID;

public record Customer(
        UUID id,
        String email,
        String firstName,
        String lastName,
        String phone,
        LocalDate birthDate,
        String preferencesJson,
        Instant createdAt,
        Instant updatedAt
) {
}
