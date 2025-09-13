package com.stayndine.customers.infrastructure.out.persistence.jpa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import static org.hibernate.type.SqlTypes.BINARY;

@Entity
@Table(name = "customer", uniqueConstraints = {
        @UniqueConstraint(name = "uk_customer_email", columnNames = "email")
})
@Getter
@Setter
public class CustomerEntity {

    @Id
    @JdbcTypeCode(BINARY)
    private UUID id;

    @JdbcTypeCode(BINARY)
    @Column(name = "user_id", nullable = false)
    private UUID userId;

    @Column(nullable = false, length = 180)
    private String email;

    @Column(name = "first_name", nullable = false, length = 120)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 120)
    private String lastName;

    @Column(length = 40)
    private String phone;

    private LocalDate birthDate;

    @Lob
    @Column(name = "preferences_json")
    private String preferencesJson;

    @Column(nullable = false)
    private Instant createdAt;

    @Column(nullable = false)
    private Instant updatedAt;
}
