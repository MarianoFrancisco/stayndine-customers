package com.stayndine.customers.infrastructure.out.persistence.jpa.adapter;

import com.stayndine.customers.application.port.out.CustomerRepository;
import com.stayndine.customers.domain.model.Customer;
import com.stayndine.customers.infrastructure.out.persistence.jpa.mapper.CustomerJpaMapper;
import com.stayndine.customers.infrastructure.out.persistence.jpa.spring.CustomerJpa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryAdapter implements CustomerRepository {

    private final CustomerJpa jpa;
    private final CustomerJpaMapper mapper;

    @Override
    public Customer save(Customer customer) {
        var saved = jpa.save(mapper.toEntity(customer));
        return mapper.toDomain(saved);
    }

    @Override
    public Optional<Customer> findById(UUID id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return jpa.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Customer update(Customer customer) {
        var saved = jpa.save(mapper.toEntity(customer));
        return mapper.toDomain(saved);
    }
}
