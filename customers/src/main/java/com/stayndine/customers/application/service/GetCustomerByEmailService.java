package com.stayndine.customers.application.service;

import com.stayndine.customers.application.port.in.query.GetCustomerByEmailQuery;
import com.stayndine.customers.application.port.out.CustomerRepository;
import com.stayndine.customers.domain.model.Customer;
import com.stayndine.customers.infrastructure.in.error.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetCustomerByEmailService implements GetCustomerByEmailQuery {
    private final CustomerRepository repo;

    @Override
    public Customer handle(String email) {
        return repo.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("customer not found by email"));
    }
}
