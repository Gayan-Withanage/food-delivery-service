import React, { useEffect, useState } from "react";
import { api, API_KEYS } from "../api";

export default function Payments() {
  const [payments, setPayments] = useState([]);
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    api
      .get("/api/payments/history", { headers: { "X-API-KEY": API_KEYS.payment } })
      .then((res) => setPayments(res.data))
      .catch((err) => setError(err.response?.data?.error || "Couldn't load payment history."))
      .finally(() => setLoading(false));
  }, []);

  return (
    <div className="page">
      <h1 className="page-title">Payments</h1>
      <p className="page-subtitle">Every charge, receipt-style.</p>

      {loading && <p className="loading-text">Loading…</p>}
      {error && <p className="form-error">{error}</p>}
      {!loading && !error && payments.length === 0 && <div className="empty-state">No payments yet.</div>}

      {(Array.isArray(payments) ? payments : []).map((p, i) => (
        <div className="ticket" key={p.id || i}>
          <div className="ticket-main">
            <strong className="price">${p.amount}</strong>
            <div className="ticket-meta">Order {p.orderId}</div>
          </div>
          <span className={"status" + (p.status === "SUCCESS_TODO" || p.status === "SUCCESS" ? " status-ok" : "")}>
            {p.status || "SUCCESS"}
          </span>
        </div>
      ))}
    </div>
  );
}