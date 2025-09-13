package com.stayndine.customers.infrastructure.out.persistence.jpa.mapper;

import com.stayndine.customers.domain.model.Customer;
import com.stayndine.customers.infrastructure.out.persistence.jpa.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CustomerJpaMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "userId")
    Customer toDomain(CustomerEntity e);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "userId", source = "userId")
    CustomerEntity toEntity(Customer c);
}
