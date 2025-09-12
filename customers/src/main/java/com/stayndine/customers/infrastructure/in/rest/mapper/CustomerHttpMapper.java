package com.stayndine.customers.infrastructure.in.rest.mapper;

import com.stayndine.customers.domain.model.Customer;
import com.stayndine.customers.infrastructure.in.rest.dto.CustomerCreateRequest;
import com.stayndine.customers.infrastructure.in.rest.dto.CustomerResponse;
import com.stayndine.customers.infrastructure.in.rest.dto.CustomerUpdateRequest;
import org.mapstruct.*;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerHttpMapper {

    @Mapping(target = "id", expression = "java(c.id().toString())")
    CustomerResponse toResponse(Customer c);

}
