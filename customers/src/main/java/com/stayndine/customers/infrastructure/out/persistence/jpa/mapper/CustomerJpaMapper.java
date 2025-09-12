package com.stayndine.customers.infrastructure.out.persistence.jpa.mapper;

import com.stayndine.customers.domain.model.Customer;
import com.stayndine.customers.infrastructure.out.persistence.jpa.entity.CustomerEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CustomerJpaMapper {
    Customer toDomain(CustomerEntity e);

    @Mappings({
            @Mapping(target = "id", source = "id"),
            @Mapping(target = "email", source = "email"),
            @Mapping(target = "firstName", source = "firstName"),
            @Mapping(target = "lastName", source = "lastName"),
            @Mapping(target = "phone", source = "phone"),
            @Mapping(target = "birthDate", source = "birthDate"),
            @Mapping(target = "preferencesJson", source = "preferencesJson"),
            @Mapping(target = "createdAt", source = "createdAt"),
            @Mapping(target = "updatedAt", source = "updatedAt")
    })
    CustomerEntity toEntity(Customer c);
}
