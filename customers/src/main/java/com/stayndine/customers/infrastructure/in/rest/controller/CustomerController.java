package com.stayndine.customers.infrastructure.in.rest.controller;

import com.stayndine.customers.application.port.in.command.UpdateCustomerProfileUseCase;
import com.stayndine.customers.application.port.in.query.GetCustomerByEmailQuery;
import com.stayndine.customers.application.port.in.query.GetCustomerByIdQuery;
import com.stayndine.customers.application.port.in.query.GetCustomerByUserIdQuery;
import com.stayndine.customers.infrastructure.in.error.ForbiddenException;
import com.stayndine.customers.infrastructure.in.rest.dto.CustomerResponse;
import com.stayndine.customers.infrastructure.in.rest.dto.CustomerUpdateRequest;
import com.stayndine.customers.infrastructure.in.rest.mapper.CustomerHttpMapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final UpdateCustomerProfileUseCase update;
    private final GetCustomerByIdQuery getById;
    private final GetCustomerByEmailQuery getByEmail;
    private final GetCustomerByUserIdQuery getByUserId;
    private final CustomerHttpMapper mapper;

    @GetMapping("/me")
    public ResponseEntity<CustomerResponse> me(Authentication authentication) {
        var jwt = (JwtAuthenticationToken) authentication;
        var userId = UUID.fromString(jwt.getName());
        var c = getByUserId.handle(userId);
        return ResponseEntity.ok(mapper.toResponse(c));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER','MANAGER','EMPLOYEE')")
    public ResponseEntity<CustomerResponse> byId(@PathVariable UUID id, Authentication authentication) {
        var c = getById.handle(id);
        if (!isStaff(authentication)) {
            var userId = UUID.fromString(((JwtAuthenticationToken) authentication).getName());
            if (c.userId() == null || !c.userId().equals(userId)) {
                throw new ForbiddenException("not owner");
            }
        }
        return ResponseEntity.ok(mapper.toResponse(c));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('CUSTOMER','MANAGER')")
    public ResponseEntity<CustomerResponse> update(
            @PathVariable UUID id, @Valid @RequestBody CustomerUpdateRequest body, Authentication authentication) {

        var c = getById.handle(id);
        if (!isStaff(authentication)) {
            var userId = UUID.fromString(((JwtAuthenticationToken) authentication).getName());
            if (c.userId() == null || !c.userId().equals(userId)) {
                throw new ForbiddenException("not owner");
            }
        }
        var updated = update.handle(new UpdateCustomerProfileUseCase.Command(
                id, body.firstName(), body.lastName(), body.phone(), body.birthDate(), body.preferencesJson()
        ));
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @GetMapping("/by-email")
    @PreAuthorize("hasAnyRole('MANAGER','EMPLOYEE')")
    public ResponseEntity<CustomerResponse> byEmail(@RequestParam String email) {
        var c = getByEmail.handle(email);
        return ResponseEntity.ok(mapper.toResponse(c));
    }

    private boolean isStaff(Authentication auth) {
        return auth.getAuthorities().stream().anyMatch(a ->
                a.getAuthority().equals("ROLE_MANAGER") || a.getAuthority().equals("ROLE_EMPLOYEE"));
    }
}
