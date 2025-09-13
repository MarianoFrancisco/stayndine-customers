package com.stayndine.customers.infrastructure.in.rest.dto.internal;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record InternalCustomerCreateRequest(
        @NotNull UUID userId,
        @NotBlank @Email String email,
        @NotBlank String firstName,
        @NotBlank String lastName,
        String phone,
        LocalDate birthDate
) {}
