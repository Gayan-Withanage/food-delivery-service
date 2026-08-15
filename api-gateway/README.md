# API Gateway

Owner: **Student 1 — Gateway Lead**

Single entry point for the whole system. The client app talks ONLY to this
gateway (http://localhost:8080), never directly to a microservice.

## What's already scaffolded
- Spring Cloud Gateway routes to all 6 backend services (`config/RouteConfig.java`)
- CORS filter allowing your client's origin (`config/CorsConfig.java`)
- A simple in-memory per-IP rate limiter, 20 req/min (`config/RateLimitFilter.java`)
- A security filter chain stub that currently `permitAll()`s everything

## Your checklist (this is the highest-effort piece — start early)
- [ ] Implement real JWT validation (see TODO in `config/SecurityConfig.java`) so
      protected routes reject requests without a valid token from auth-service
- [ ] Update `jwt.secret` to match whatever auth-service uses (share via .env,
      don't hardcode the real secret in git)
- [ ] Update the hostnames in `RouteConfig.java` to your docker-compose service
      names (already set to e.g. `http://restaurant-service:8082`)
- [ ] Update `CorsConfig.java` allowed origins to match your actual client app port
- [ ] Test rate limiting: hammer an endpoint with e.g. Postman Runner or a
      simple loop and confirm you get HTTP 429 after 20 requests/min
- [ ] Write the "API Gateway & Security" section of the report: OAuth2 flow
      diagram, CORS rules, rate limiting strategy
- [ ] Draw the overall architecture diagram (client -> gateway -> 6 services -> mongo)
