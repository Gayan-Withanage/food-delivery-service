# Auth Service (+ Gateway Lead)

Owner: **Student 1 — Gateway Lead / User & Auth Service**

This service handles registration/login and issues JWTs. You are also
responsible for the `api-gateway` folder (OAuth2, CORS, Rate Limiting).

## Endpoints scaffolded
- POST /auth/register
- POST /auth/login  -> returns a JWT

## Run locally
1. `docker compose up mongo-auth` (from project root) or run local MongoDB
2. `./mvnw spring-boot:run` (or `mvn spring-boot:run`)
3. Swagger UI: http://localhost:8081/swagger-ui.html

## Your checklist
- [ ] Finish register/login validation (duplicate email, password rules)
- [ ] Decide + implement the real OAuth2 flow at the Gateway level (see api-gateway/README.md)
- [ ] Make the Gateway validate the JWT this service issues (or swap to a proper
      OAuth2 Authorization Server such as Spring Authorization Server / Keycloak —
      pick ONE approach and document it in the report, don't do both)
- [ ] Add role-based access (CUSTOMER vs RIDER vs ADMIN) if your team wants it
- [ ] Write the "System Architecture & Gateway Design" + "Security" sections of the report
- [ ] Draw the architecture diagram for the README (see root README TODO)
- [ ] Test in Postman, export collection to /postman/auth-service.postman_collection.json
