package com.stayndine.customers.application.service;

import com.stayndine.customers.application.port.in.query.GetCustomerByUserIdQuery;
import com.stayndine.customers.application.port.out.CustomerRepository;
import com.stayndine.customers.domain.model.Customer;
import com.stayndine.customers.infrastructure.in.error.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class GetCustomerByUserIdService implements GetCustomerByUserIdQuery {

    private final CustomerRepository repo;

    @Override
    public Customer handle(UUID userId) {
        return repo.findByUserId(userId)
                .orElseThrow(() -> new ResourceNotFoundException("customer not found for user"));
    }
}
