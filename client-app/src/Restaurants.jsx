import React, { useEffect, useState } from "react";
import { api, clearSession, getSession } from "./api";

const RESTAURANT_API_KEY = "restaurant-service-secret-key-change-me";

export default function Restaurants({ onLoggedOut }) {
  const [restaurants, setRestaurants] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);
  const { username, role } = getSession();

  useEffect(() => {
    api
      .get("/api/restaurants", { headers: { "X-API-KEY": RESTAURANT_API_KEY } })
      .then((res) => setRestaurants(res.data))
      .catch((err) => setError(err.response?.data?.error || "Failed to load restaurants."))
      .finally(() => setLoading(false));
  }, []);

  const logout = () => {
    clearSession();
    onLoggedOut();
  };

  return (
    <div style={{ maxWidth: 640, margin: "40px auto", fontFamily: "sans-serif" }}>
      <div style={{ display: "flex", justifyContent: "space-between", alignItems: "center" }}>
        <h2>Restaurants</h2>
        <div>
          <span style={{ marginRight: 12 }}>
            Logged in as <b>{username}</b> ({role})
          </span>
          <button onClick={logout}>Log out</button>
        </div>
      </div>

      {loading && <p>Loading...</p>}
      {error && <p style={{ color: "crimson" }}>{error}</p>}

      {!loading && !error && restaurants.length === 0 && (
        <p>No restaurants yet — add one via Postman or Swagger to see it appear here.</p>
      )}

      <ul style={{ listStyle: "none", padding: 0 }}>
        {restaurants.map((r, i) => (
          <li key={r.id || i} style={{ border: "1px solid #ddd", borderRadius: 8, padding: 12, marginBottom: 10 }}>
            <strong>{r.name}</strong> — {r.cuisine}
            <br />
            <small>{r.address} · ⭐ {r.rating}</small>
          </li>
        ))}
      </ul>
    </div>
  );
}