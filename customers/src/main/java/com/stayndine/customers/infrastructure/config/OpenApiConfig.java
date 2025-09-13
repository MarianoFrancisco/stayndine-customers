package com.stayndine.customers.infrastructure.config;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Stayndine Customers").version("v1"))
                .addSecurityItem(new SecurityRequirement().addList("bearer-jwt"))
                .components(new Components().addSecuritySchemes("bearer-jwt",
                        new SecurityScheme().name("bearer-jwt").type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));
    }

    @Bean
    public GroupedOpenApi identityGroup() {
        return GroupedOpenApi.builder()
                .group("identity")
                .packagesToScan(
                        "com.stayndine.customers.infrastructure.in.rest.controller",
                        "com.stayndine.customers.infrastructure.in.internal.controller"
                )
                .pathsToMatch("/api/**", "/internal/**")
                .build();
    }
}
