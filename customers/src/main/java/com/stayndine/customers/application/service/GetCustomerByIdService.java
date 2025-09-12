package com.stayndine.customers.application.service;

import com.stayndine.customers.application.port.in.query.GetCustomerByIdQuery;
import com.stayndine.customers.application.port.out.CustomerRepository;
import com.stayndine.customers.domain.model.Customer;
import com.stayndine.customers.infrastructure.in.error.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCustomerByIdService implements GetCustomerByIdQuery {
    private final CustomerRepository repo;

    @Override
    public Customer handle(UUID id) {
        return repo.findById(id).orElseThrow(() -> new ResourceNotFoundException("customer not found"));
    }
}
