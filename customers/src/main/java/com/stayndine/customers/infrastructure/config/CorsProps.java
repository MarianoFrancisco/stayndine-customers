package com.stayndine.customers.infrastructure.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "cors")
public record CorsProps(
        List<String> allowedOrigins,
        List<String> allowedMethods,
        List<String> allowedHeaders,
        List<String> exposedHeaders,
        Boolean allowCredentials
) {
}
