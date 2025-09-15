package com.stayndine.customers.application.port.in.query;

import java.util.UUID;

import com.stayndine.customers.domain.model.Customer;

public interface GetCustomerByIdQuery {
    Customer handle(UUID id);
}
