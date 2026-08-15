# Payment Service

Owner: **Student 6 — Payment Service**

## What you need to build
This is a starter skeleton only. It compiles and runs, but the business logic
is intentionally left as TODOs for you to complete.

Endpoints already scaffolded:
- POST /api/payments/process
- GET /api/payments/history
- GET /api/payments/{id}

## Run locally (without Docker)
1. Install a local MongoDB, or just run `docker compose up mongo-payment-service` from the project root.
2. `./mvnw spring-boot:run`
3. Swagger UI: http://localhost:8086/swagger-ui.html
4. Every request (except swagger/actuator) needs header: `X-API-KEY: payment-service-secret-key-change-me`

## Your checklist (marking criteria mapping)
- [ ] Implement real CRUD logic in `PaymentController` + `PaymentService` (Microservices & API Design — 30%)
- [ ] Keep the `ApiKeyFilter` working — do not remove it (API Key Security requirement)
- [ ] Add request/response DTOs with validation (`@Valid`, `@NotBlank`, etc.)
- [ ] Add unit tests for your service class
- [ ] Fill in the `## Payment Service` section of the group report (endpoints, schema, sample requests/responses)
- [ ] Test every endpoint in Postman and export your Postman collection into `/postman/payment-service.postman_collection.json`
- [ ] Make sure `docker build .` works from this folder before merging to main
- [ ] Commit early and often under your own name/email so the commit history shows your individual contribution
