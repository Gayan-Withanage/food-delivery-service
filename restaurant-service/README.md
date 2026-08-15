# Restaurant Service

Owner: **Student 2 — Restaurant & Menu Service**

## What you need to build
This is a starter skeleton only. It compiles and runs, but the business logic
is intentionally left as TODOs for you to complete.

Endpoints already scaffolded:
- GET /api/restaurants
- GET /api/restaurants/{id}
- POST /api/restaurants
- GET /api/restaurants/{id}/menu
- DELETE /api/restaurants/{id}

## Run locally (without Docker)
1. Install a local MongoDB, or just run `docker compose up mongo-restaurant-service` from the project root.
2. `./mvnw spring-boot:run`
3. Swagger UI: http://localhost:8082/swagger-ui.html
4. Every request (except swagger/actuator) needs header: `X-API-KEY: restaurant-service-secret-key-change-me`

## Your checklist (marking criteria mapping)
- [ ] Implement real CRUD logic in `RestaurantController` + `RestaurantService` (Microservices & API Design — 30%)
- [ ] Keep the `ApiKeyFilter` working — do not remove it (API Key Security requirement)
- [ ] Add request/response DTOs with validation (`@Valid`, `@NotBlank`, etc.)
- [ ] Add unit tests for your service class
- [ ] Fill in the `## Restaurant Service` section of the group report (endpoints, schema, sample requests/responses)
- [ ] Test every endpoint in Postman and export your Postman collection into `/postman/restaurant-service.postman_collection.json`
- [ ] Make sure `docker build .` works from this folder before merging to main
- [ ] Commit early and often under your own name/email so the commit history shows your individual contribution
