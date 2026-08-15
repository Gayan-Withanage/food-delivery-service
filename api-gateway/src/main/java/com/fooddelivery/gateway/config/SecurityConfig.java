package com.fooddelivery.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * TODO (Student 1 - Gateway Lead): This currently permits everything so the
 * team can get end-to-end plumbing working first. Before submission:
 *   1. Require a valid Bearer JWT (issued by auth-service /auth/login) on
 *      every route EXCEPT /auth/register and /auth/login.
 *   2. Document your chosen OAuth2 flow (Authorization Code or Client
 *      Credentials) and how token issuance/validation works end-to-end
 *      in the report's "Security & Infrastructure" section.
 *   Two valid implementation options:
 *     a) Treat auth-service's JWT as the OAuth2 "access token" and configure
 *        this gateway as an oauth2ResourceServer with the same signing secret
 *        (jwt.secret) — simplest for a student project.
 *     b) Stand up a real Authorization Server (Spring Authorization Server or
 *        Keycloak) issuing standards-compliant OAuth2 tokens, and have
 *        auth-service delegate to it. More marks for "real" OAuth2, more setup.
 */
@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeExchange(exchange -> exchange
                .pathMatchers("/auth/register", "/auth/login").permitAll()
                // TODO: switch this to .authenticated() once JWT validation is wired up
                .anyExchange().permitAll()
            );
        return http.build();
    }
}
