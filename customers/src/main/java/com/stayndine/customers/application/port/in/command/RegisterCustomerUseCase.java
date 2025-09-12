package com.stayndine.customers.application.port.in.command;

import com.stayndine.customers.domain.model.Customer;

import java.time.LocalDate;

public interface RegisterCustomerUseCase {
    record Command(String email, String firstName, String lastName, String phone, LocalDate birthDate) {
    }

    Customer handle(Command cmd);
}
