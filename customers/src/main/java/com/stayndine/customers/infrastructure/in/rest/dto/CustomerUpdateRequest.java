package com.stayndine.customers.infrastructure.in.rest.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record CustomerUpdateRequest(
        @NotBlank String firstName,
        @NotBlank String lastName,
        @Size(max = 32) String phone,
        LocalDate birthDate,
        String preferencesJson
) {
}
