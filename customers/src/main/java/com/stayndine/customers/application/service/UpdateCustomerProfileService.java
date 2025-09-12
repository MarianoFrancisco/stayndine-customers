package com.stayndine.customers.application.service;

import com.stayndine.customers.application.port.in.command.UpdateCustomerProfileUseCase;
import com.stayndine.customers.application.port.out.CustomerRepository;
import com.stayndine.customers.domain.model.Customer;
import com.stayndine.customers.infrastructure.in.error.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class UpdateCustomerProfileService implements UpdateCustomerProfileUseCase {
    private final CustomerRepository customerRepository;

    @Override
    @Transactional
    public Customer handle(Command cmd) {
        var existing = customerRepository.findById(cmd.id())
                .orElseThrow(() -> new ResourceNotFoundException("customer not found"));
        var updated = new Customer(
                existing.id(),
                existing.email(),
                cmd.firstName().trim(),
                cmd.lastName().trim(),
                cmd.phone(),
                cmd.birthDate(),
                cmd.preferencesJson(),
                existing.createdAt(),
                Instant.now()
        );
        return customerRepository.update(updated);
    }
}
