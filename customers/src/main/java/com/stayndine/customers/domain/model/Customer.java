package com.stayndine.customers.domain.model;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

public record Customer(
        UUID id,
        UUID userId,
        String email,
        String firstName,
        String lastName,
        String phone,
        LocalDate birthDate,
        String preferencesJson,
        Instant createdAt,
        Instant updatedAt
) {}
