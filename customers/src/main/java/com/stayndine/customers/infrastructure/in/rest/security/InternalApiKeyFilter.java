package com.stayndine.customers.infrastructure.in.security;

import com.stayndine.customers.infrastructure.config.InternalAuthProperties;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Set;

@RequiredArgsConstructor
public class InternalApiKeyFilter extends OncePerRequestFilter {

    private final InternalAuthProperties props;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        try {
            String path = req.getRequestURI();
            if (path.startsWith("/internal/")) {
                String apiKey = req.getHeader("X-Internal-Api-Key");
                if (StringUtils.hasText(apiKey) && apiKey.equals(props.apiKey())) {
                    var auth = new AbstractAuthenticationToken(Set.of(new SimpleGrantedAuthority("ROLE_INTERNAL"))) {
                        @Override
                        public Object getCredentials() {
                            return apiKey;
                        }

                        @Override
                        public Object getPrincipal() {
                            return "internal-client";
                        }
                    };
                    auth.setAuthenticated(true);
                    SecurityContextHolder.getContext().setAuthentication(auth);
                } else {
                    res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    res.setHeader(HttpHeaders.WWW_AUTHENTICATE, "ApiKey realm=\"internal\"");
                    return;
                }
            }
        } catch (Exception e) {
            SecurityContextHolder.clearContext();
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        chain.doFilter(req, res);
    }
}
