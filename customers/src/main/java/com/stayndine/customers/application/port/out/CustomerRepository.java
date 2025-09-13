package com.stayndine.customers.application.port.out;

import com.stayndine.customers.domain.model.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Optional<Customer> findById(UUID id);

    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByUserId(UUID userId);

    void save(Customer customer);

    void update(Customer customer);
}