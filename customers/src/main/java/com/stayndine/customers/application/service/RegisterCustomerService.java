package com.stayndine.customers.application.service;

import com.stayndine.customers.application.port.in.command.RegisterCustomerUseCase;
import com.stayndine.customers.application.port.out.CustomerRepository;
import com.stayndine.customers.domain.model.Customer;
import com.stayndine.customers.infrastructure.in.error.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RegisterCustomerService implements RegisterCustomerUseCase {
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer handle(Command cmd) {
        customerRepository.findByEmail(cmd.email()).ifPresent(c -> {
            throw new ValidationException("email already registered");
        });
        var now = Instant.now();
        var c = new Customer(
                UUID.randomUUID(),
                cmd.email().trim().toLowerCase(),
                cmd.firstName().trim(),
                cmd.lastName().trim(),
                cmd.phone(),
                cmd.birthDate(),
                null,
                now, now
        );
        return customerRepository.save(c);
    }
}
