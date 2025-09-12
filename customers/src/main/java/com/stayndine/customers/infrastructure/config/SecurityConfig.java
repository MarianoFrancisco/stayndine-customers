package com.stayndine.customers.infrastructure.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder; // opcional si más adelante firmas tokens aquí (no lo usamos ahora)
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(reg -> reg
                        .requestMatchers(
                                "/actuator/health", "/actuator/info",
                                "/v3/api-docs/**", "/swagger-ui.html", "/swagger-ui/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/customers").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth -> oauth.jwt(jwt -> jwt.jwtAuthenticationConverter(this::convertJwt)))
                .build();
    }

    private JwtAuthenticationToken convertJwt(Jwt jwt) {
        List<String> roles = jwt.getClaimAsStringList("roles");
        Collection<SimpleGrantedAuthority> authorities = roles == null ? List.of()
                : roles.stream()
                .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                .collect(Collectors.toSet());
        return new JwtAuthenticationToken(jwt, authorities, jwt.getSubject());
    }

    @Bean
    JwtDecoder jwtDecoder(@Value("${security.jwt.secret}") String secret) {
        var keyBytes = secret.getBytes(StandardCharsets.UTF_8);
        var secretKey = new SecretKeySpec(keyBytes, "HmacSHA256");
        return NimbusJwtDecoder.withSecretKey(secretKey)
                .macAlgorithm(MacAlgorithm.HS256)
                .build();
    }
}
