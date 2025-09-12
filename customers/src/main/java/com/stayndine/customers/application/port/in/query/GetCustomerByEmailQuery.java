package com.stayndine.customers.application.port.in.query;

import com.stayndine.customers.domain.model.Customer;

public interface GetCustomerByEmailQuery {
    Customer handle(String email);
}
