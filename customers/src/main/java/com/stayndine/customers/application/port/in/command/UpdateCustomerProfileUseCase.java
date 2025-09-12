package com.stayndine.customers.application.port.in.command;

import com.stayndine.customers.domain.model.Customer;

import java.time.LocalDate;
import java.util.UUID;

public interface UpdateCustomerProfileUseCase {
    record Command(UUID id, String firstName, String lastName, String phone, LocalDate birthDate,
                   String preferencesJson) {
    }

    Customer handle(Command cmd);
}
