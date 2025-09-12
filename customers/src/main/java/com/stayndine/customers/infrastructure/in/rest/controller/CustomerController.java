package com.stayndine.customers.infrastructure.in.rest.controller;

import com.stayndine.customers.application.port.in.command.RegisterCustomerUseCase;
import com.stayndine.customers.application.port.in.command.UpdateCustomerProfileUseCase;
import com.stayndine.customers.application.port.in.query.GetCustomerByEmailQuery;
import com.stayndine.customers.application.port.in.query.GetCustomerByIdQuery;
import com.stayndine.customers.infrastructure.in.rest.dto.*;
import com.stayndine.customers.infrastructure.in.rest.mapper.CustomerHttpMapper;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearer-jwt")
public class CustomerController {

    private final RegisterCustomerUseCase register;
    private final UpdateCustomerProfileUseCase update;
    private final GetCustomerByIdQuery getById;
    private final GetCustomerByEmailQuery getByEmail;
    private final CustomerHttpMapper mapper;

    @PostMapping
    @SecurityRequirement(name = "")
    public ResponseEntity<CustomerResponse> create(@Valid @RequestBody CustomerCreateRequest body) {
        var c = register.handle(new RegisterCustomerUseCase.Command(
                body.email(), body.firstName(), body.lastName(), body.phone(), body.birthDate()
        ));
        return ResponseEntity.ok(mapper.toResponse(c));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER','MANAGER','EMPLOYEE')")
    public ResponseEntity<CustomerResponse> byId(@PathVariable UUID id) {
        var c = getById.handle(id);
        return ResponseEntity.ok(mapper.toResponse(c));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER','MANAGER')")
    public ResponseEntity<CustomerResponse> update(
            @PathVariable UUID id, @Valid @RequestBody CustomerUpdateRequest body) {
        var c = update.handle(new UpdateCustomerProfileUseCase.Command(
                id, body.firstName(), body.lastName(), body.phone(), body.birthDate(), body.preferencesJson()
        ));
        return ResponseEntity.ok(mapper.toResponse(c));
    }

    @GetMapping("/by-email")
    @PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
    public ResponseEntity<CustomerResponse> byEmail(@RequestParam String email) {
        var c = getByEmail.handle(email);
        return ResponseEntity.ok(mapper.toResponse(c));
    }
}
