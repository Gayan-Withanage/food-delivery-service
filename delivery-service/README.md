# Delivery Service

Owner: **Student 4 — Delivery Service**

## What you need to build
This is a starter skeleton only. It compiles and runs, but the business logic
is intentionally left as TODOs for you to complete.

Endpoints already scaffolded:
- GET /api/delivery
- GET /api/delivery/{id}
- POST /api/delivery/assign
- PUT /api/delivery/{id}/status

## Run locally (without Docker)
1. Install a local MongoDB, or just run `docker compose up mongo-delivery-service` from the project root.
2. `./mvnw spring-boot:run`
3. Swagger UI: http://localhost:8084/swagger-ui.html
4. Every request (except swagger/actuator) needs header: `X-API-KEY: delivery-service-secret-key-change-me`

## Your checklist (marking criteria mapping)
- [ ] Implement real CRUD logic in `DeliveryController` + `DeliveryService` (Microservices & API Design — 30%)
- [ ] Keep the `ApiKeyFilter` working — do not remove it (API Key Security requirement)
- [ ] Add request/response DTOs with validation (`@Valid`, `@NotBlank`, etc.)
- [ ] Add unit tests for your service class
- [ ] Fill in the `## Delivery Service` section of the group report (endpoints, schema, sample requests/responses)
- [ ] Test every endpoint in Postman and export your Postman collection into `/postman/delivery-service.postman_collection.json`
- [ ] Make sure `docker build .` works from this folder before merging to main
- [ ] Commit early and often under your own name/email so the commit history shows your individual contribution
