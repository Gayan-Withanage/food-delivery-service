package com.fooddelivery.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Central routing table. Every request to the client app goes through here
 * and is forwarded to the right backend microservice.
 * Update the "uri" host/port to match your docker-compose service names once
 * you containerize (e.g. http://auth-service:8081 instead of localhost).
 */
@Configuration
public class RouteConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("auth-service", r -> r.path("/auth/**")
                        .uri("http://auth-service:8081"))
                .route("restaurant-service", r -> r.path("/api/restaurants/**")
                        .filters(f -> f.filter(new RateLimitFilter()))
                        .uri("http://restaurant-service:8082"))
                .route("order-service", r -> r.path("/api/orders/**")
                        .filters(f -> f.filter(new RateLimitFilter()))
                        .uri("http://order-service:8083"))
                .route("delivery-service", r -> r.path("/api/delivery/**")
                        .filters(f -> f.filter(new RateLimitFilter()))
                        .uri("http://delivery-service:8084"))
                .route("notification-service", r -> r.path("/api/notify/**")
                        .filters(f -> f.filter(new RateLimitFilter()))
                        .uri("http://notification-service:8085"))
                .route("payment-service", r -> r.path("/api/payments/**")
                        .filters(f -> f.filter(new RateLimitFilter()))
                        .uri("http://payment-service:8086"))
                .build();
    }
}
