package com.fooddelivery.auth.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Requires X-API-KEY on every route of this service, including /auth/register
 * and /auth/login. This is a separate, service-level secret shared by the
 * client application — it identifies "this is a legitimate client of the
 * system" and is independent of the per-user JWT issued after login.
 */
@Component
public class ApiKeyFilter extends OncePerRequestFilter {

    @Value("${service.api-key}")
    private String expectedApiKey;

    private static final String HEADER_NAME = "X-API-KEY";

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String path = request.getRequestURI();
        if (path.contains("/swagger-ui") || path.contains("/v3/api-docs") || path.contains("/actuator")) {
            filterChain.doFilter(request, response);
            return;
        }

        String providedKey = request.getHeader(HEADER_NAME);
        if (providedKey == null || !providedKey.equals(expectedApiKey)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\":\"Missing or invalid API Key\"}");
            return;
        }

        filterChain.doFilter(request, response);
    }
}