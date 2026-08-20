import React, { useEffect, useState } from "react";
import { api, API_KEYS } from "../api";

export default function Orders() {
  const [orders, setOrders] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api
      .get("/api/orders", { headers: { "X-API-KEY": API_KEYS.order } })
      .then((res) => setOrders(res.data))
      .catch((err) => setError(err.response?.data?.error || "Couldn't load orders."))
      .finally(() => setLoading(false));
  }, []);

  return (
    <div className="page">
      <h1 className="page-title">Your orders</h1>
      <p className="page-subtitle">Track what's cooking.</p>

      {loading && <p className="loading-text">Loading…</p>}
      {error && <p className="form-error">{error}</p>}
      {!loading && !error && orders.length === 0 && (
        <div className="empty-state">No orders yet — place one from your Cart.</div>
      )}

      {(Array.isArray(orders) ? orders : []).map((o, i) => (
        <div className="ticket" key={o.id || i}>
          <div className="ticket-main">
            <strong>Order #{o.id || i + 1}</strong>
            <div className="ticket-meta price">${o.totalAmount?.toFixed?.(2) ?? o.totalAmount}</div>
          </div>
          <span className={"status" + (o.status === "DELIVERED" ? " status-ok" : "")}>{o.status || "CREATED"}</span>
        </div>
      ))}
    </div>
  );
}