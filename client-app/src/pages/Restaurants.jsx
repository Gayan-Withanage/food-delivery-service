import React, { useEffect, useState } from "react";
import { api, API_KEYS, addToCart } from "../api";

export default function Restaurants() {
  const [restaurants, setRestaurants] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);
  const [added, setAdded] = useState("");

  useEffect(() => {
    api
      .get("/api/restaurants", { headers: { "X-API-KEY": API_KEYS.restaurant } })
      .then((res) => setRestaurants(res.data))
      .catch((err) => setError(err.response?.data?.error || "Couldn't load restaurants."))
      .finally(() => setLoading(false));
  }, []);

  const handleAdd = (r) => {
    addToCart({ restaurantId: r.id, name: r.name, price: 10 });
    setAdded(`${r.name} added to cart`);
    setTimeout(() => setAdded(""), 1800);
  };

  return (
    <div className="page">
      <h1 className="page-title">Restaurants</h1>
      <p className="page-subtitle">Pick somewhere good.</p>

      {loading && <p className="loading-text">Loading…</p>}
      {error && <p className="form-error">{error}</p>}
      {added && <p className="form-note">{added}</p>}

      {!loading && !error && restaurants.length === 0 && (
        <div className="empty-state">No restaurants yet — add one via Postman or Swagger to see it appear here.</div>
      )}

      {restaurants.map((r, i) => (
        <div className="ticket" key={r.id || i}>
          <div className="ticket-main">
            <strong>{r.name}</strong>
            <div className="ticket-meta">{r.cuisine} · {r.address} · ⭐ {r.rating}</div>
          </div>
          <button className="btn btn-primary btn-small" onClick={() => handleAdd(r)}>Add to cart</button>
        </div>
      ))}
    </div>
  );
}