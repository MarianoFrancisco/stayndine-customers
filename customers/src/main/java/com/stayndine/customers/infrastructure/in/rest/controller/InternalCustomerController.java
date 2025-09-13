package com.stayndine.customers.infrastructure.in.rest.controller;

import com.stayndine.customers.infrastructure.in.rest.dto.internal.InternalCustomerCreateRequest;
import com.stayndine.customers.application.port.in.command.RegisterCustomerUseCase;
import com.stayndine.customers.infrastructure.in.rest.dto.CustomerResponse;
import com.stayndine.customers.infrastructure.in.rest.mapper.CustomerHttpMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("/internal/customers")
@Validated
public class InternalCustomerController {

    private final RegisterCustomerUseCase register;
    private final CustomerHttpMapper mapper;

    public InternalCustomerController(RegisterCustomerUseCase register, CustomerHttpMapper mapper) {
        this.register = register;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<CustomerResponse> createByBff(
            @Valid @RequestBody InternalCustomerCreateRequest body) {
        var created = register.handle(new RegisterCustomerUseCase.Command(
                body.userId(), body.email(), body.firstName(), body.lastName(), body.phone(), body.birthDate()
        ));
        return ResponseEntity.ok(mapper.toResponse(created));
    }
}
