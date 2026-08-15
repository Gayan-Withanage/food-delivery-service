# Food Delivery System — Microservices Coursework

A distributed food-delivery platform built with Spring Boot microservices,
a Spring Cloud API Gateway, MongoDB, and Docker.

## Team & Ownership

| Student | Role | Microservice | Folder |
|---|---|---|---|
| Student 1 | Gateway Lead | User & Auth Service | `auth-service/`, `api-gateway/` |
| Student 2 | Member | Restaurant Service | `restaurant-service/` |
| Student 3 | Member | Order Service | `order-service/` |
| Student 4 | Member | Delivery Service | `delivery-service/` |
| Student 5 | Member | Notification Service | `notification-service/` |
| Student 6 | Member | Payment Service | `payment-service/` |

> Replace "Student N" with real names before submission — the brief requires
> explicit ownership declared in both the report and the repo.

## Architecture

```
                     ┌─────────────┐
                     │ Client App  │  (React/Vue/etc — client-app/)
                     └──────┬──────┘
                            │ HTTPS
                     ┌──────▼──────┐
                     │ API Gateway │  :8080  (OAuth2 + CORS + Rate Limit)
                     └──────┬──────┘
        ┌──────────┬────────┼────────┬──────────┬──────────┐
        ▼          ▼        ▼        ▼          ▼          ▼
   auth-service  restaurant order  delivery  notification  payment
     :8081        :8082    :8083    :8084       :8085       :8086
        │            │        │        │           │           │
        ▼            ▼        ▼        ▼           ▼           ▼
   mongo-auth   mongo-rest mongo-order mongo-del mongo-notif mongo-pay
```

(Replace this ASCII diagram with a proper draw.io / Lucidchart / Excalidraw
image for the report and for this README — the brief explicitly asks for an
architecture diagram.)

## Prerequisites
- Docker & Docker Compose
- Java 17 + Maven (only needed if running a service outside Docker)
- Postman (for testing / your submitted collections)

## Run everything
```bash
docker compose up --build
```

This starts all 6 microservices, the gateway, and 6 MongoDB instances.

| Service | Swagger UI |
|---|---|
| Auth Service | http://localhost:8081/swagger-ui.html |
| Restaurant Service | http://localhost:8082/swagger-ui.html |
| Order Service | http://localhost:8083/swagger-ui.html |
| Delivery Service | http://localhost:8084/swagger-ui.html |
| Notification Service | http://localhost:8085/swagger-ui.html |
| Payment Service | http://localhost:8086/swagger-ui.html |
| API Gateway (client entry point) | http://localhost:8080 |

## API Key header format
Every microservice (except the gateway) requires:
```
X-API-KEY: <service-name>-secret-key-change-me
```
e.g. `X-API-KEY: restaurant-service-secret-key-change-me` — see each
service's `application.yml` for its exact key, and change these before
submission (don't leave default secrets in your final repo).

## Repo layout
```
food-delivery-system/
├── docker-compose.yml
├── README.md
├── auth-service/          (Student 1)
├── api-gateway/           (Student 1)
├── restaurant-service/    (Student 2)
├── order-service/         (Student 3)
├── delivery-service/      (Student 4)
├── notification-service/  (Student 5)
├── payment-service/       (Student 6)
├── client-app/            (shared — everyone integrates their part)
└── postman/               (export your collections here)
```

## Team workflow
1. Each member works on their own folder in their own feature branch:
   `git checkout -b feature/<your-service-name>`
2. Commit early and often under your own name/email — commit history is graded.
3. Open a Pull Request into `main` when your service compiles, runs, and its
   Docker build succeeds. Have at least one teammate review it.
4. Once your service's endpoints are stable, tell the client-app owner(s)
   so they can wire up the UI for your part.
5. Regroup regularly (a couple of short syncs a week are enough) to make
   sure request/response shapes between services (e.g. order → payment →
   delivery) stay compatible.

See the accompanying **Project Guide PDF** for the full step-by-step plan,
a suggested week-by-week timeline, and a checklist per member.
