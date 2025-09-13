package com.stayndine.customers.application.port.in.query;

import com.stayndine.customers.domain.model.Customer;

import java.util.UUID;

public interface GetCustomerByUserIdQuery {
    Customer handle(UUID userId);
}
