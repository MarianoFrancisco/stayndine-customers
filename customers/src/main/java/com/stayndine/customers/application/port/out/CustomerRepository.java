package com.stayndine.customers.application.port.out;

import com.stayndine.customers.domain.model.Customer;

import java.util.Optional;
import java.util.UUID;

public interface CustomerRepository {
    Customer save(Customer customer);

    Optional<Customer> findById(UUID id);

    Optional<Customer> findByEmail(String email);

    Customer update(Customer customer);
}
