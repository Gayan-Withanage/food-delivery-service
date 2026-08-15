# Notification Service

Owner: **Student 5 — Notification Service**

## What you need to build
This is a starter skeleton only. It compiles and runs, but the business logic
is intentionally left as TODOs for you to complete.

Endpoints already scaffolded:
- POST /api/notify/email
- POST /api/notify/sms
- GET /api/notify/history

## Run locally (without Docker)
1. Install a local MongoDB, or just run `docker compose up mongo-notification-service` from the project root.
2. `./mvnw spring-boot:run`
3. Swagger UI: http://localhost:8085/swagger-ui.html
4. Every request (except swagger/actuator) needs header: `X-API-KEY: notification-service-secret-key-change-me`

## Your checklist (marking criteria mapping)
- [ ] Implement real CRUD logic in `NotificationController` + `NotificationService` (Microservices & API Design — 30%)
- [ ] Keep the `ApiKeyFilter` working — do not remove it (API Key Security requirement)
- [ ] Add request/response DTOs with validation (`@Valid`, `@NotBlank`, etc.)
- [ ] Add unit tests for your service class
- [ ] Fill in the `## Notification Service` section of the group report (endpoints, schema, sample requests/responses)
- [ ] Test every endpoint in Postman and export your Postman collection into `/postman/notification-service.postman_collection.json`
- [ ] Make sure `docker build .` works from this folder before merging to main
- [ ] Commit early and often under your own name/email so the commit history shows your individual contribution
