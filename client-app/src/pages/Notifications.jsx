import React, { useEffect, useState } from "react";
import { api, API_KEYS } from "../api";

export default function Notifications() {
  const [items, setItems] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api
      .get("/api/notify/history", { headers: { "X-API-KEY": API_KEYS.notification } })
      .then((res) => setItems(res.data))
      .catch((err) => setError(err.response?.data?.error || "Couldn't load notifications."))
      .finally(() => setLoading(false));
  }, []);

  return (
    <div className="page">
      <h1 className="page-title">Notifications</h1>
      <p className="page-subtitle">Everything sent your way.</p>

      {loading && <p className="loading-text">Loading…</p>}
      {error && <p className="form-error">{error}</p>}
      {!loading && !error && items.length === 0 && <div className="empty-state">No notifications yet.</div>}

      {(Array.isArray(items) ? items : []).map((n, i) => (
        <div className="ticket" key={i}>
          <div className="ticket-main">
            <strong>{n.type}</strong>
            <div className="ticket-meta">{n.to}</div>
          </div>
          <span className="status status-ok">{n.status}</span>
        </div>
      ))}
    </div>
  );
}