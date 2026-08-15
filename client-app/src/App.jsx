import React, { useEffect, useState } from "react";
import { api } from "./api";

// TODO (whole team): each member adds a section here that calls their
// own service through the gateway. This is just a smoke-test skeleton
// proving the gateway -> services wiring works end to end.
export default function App() {
  const [status, setStatus] = useState("checking gateway...");

  useEffect(() => {
    api.get("/api/restaurants", { headers: { "X-API-KEY": "restaurant-service-secret-key-change-me" } })
      .then(() => setStatus("Gateway + Restaurant Service reachable ✅"))
      .catch((err) => setStatus("Not reachable yet ❌ — is docker compose running? " + err.message));
  }, []);

  return (
    <div style={{ fontFamily: "sans-serif", padding: 24 }}>
      <h1>Food Delivery — Client App</h1>
      <p>{status}</p>
      <p>Each member: add your login/register, restaurant listing, cart/checkout,
      order tracking, or payment screen here, calling the gateway at :8080.</p>
    </div>
  );
}
