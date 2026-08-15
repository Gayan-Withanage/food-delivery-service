# Client App (shared — all members contribute their screen/section)

Minimal Vite + React starter that talks to the API Gateway only
(http://localhost:8080), never to individual microservices.

## Run
```bash
cd client-app
npm install
npm run dev
```
Then open http://localhost:3000

## Your checklist
- [ ] Login/Register screen -> auth-service via gateway
- [ ] Restaurant + menu browsing -> restaurant-service via gateway
- [ ] Cart & checkout flow -> order-service via gateway
- [ ] Order status / delivery tracking -> delivery-service via gateway
- [ ] Payment screen -> payment-service via gateway
- [ ] Notifications/toasts on key events -> notification-service via gateway
- [ ] Take the screenshots needed for the report's "Client Integration" section
- [ ] Agree on ONE person (or pair) owning final integration/merge conflicts here,
      since everyone touches this folder — coordinate via small, frequent PRs
