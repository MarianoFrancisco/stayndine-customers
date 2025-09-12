package com.stayndine.customers.infrastructure.out.persistence.jpa.spring;

import com.stayndine.customers.infrastructure.out.persistence.jpa.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CustomerJpa extends JpaRepository<CustomerEntity, UUID> {
    Optional<CustomerEntity> findByEmail(String email);
}
