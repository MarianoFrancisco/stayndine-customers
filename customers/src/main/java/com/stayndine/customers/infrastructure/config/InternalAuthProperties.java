package com.stayndine.customers.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "internal")
public record InternalAuthProperties(String apiKey) {
}
