package com.stayndine.customers.infrastructure.out.persistence.jpa.adapter;

import com.stayndine.customers.application.port.out.CustomerRepository;
import com.stayndine.customers.domain.model.Customer;
import com.stayndine.customers.infrastructure.out.persistence.jpa.entity.CustomerEntity;
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
    public Optional<Customer> findById(UUID id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return jpa.findByEmail(email).map(mapper::toDomain);
    }

    @Override
    public Optional<Customer> findByUserId(UUID userId) {
        return jpa.findByUserId(userId).map(mapper::toDomain);
    }

    @Override
    public void save(Customer c) {
        CustomerEntity e = mapper.toEntity(c);
        jpa.save(e);
    }

    @Override
    public void update(Customer c) {
        CustomerEntity e = mapper.toEntity(c);
        jpa.save(e);
    }
}
