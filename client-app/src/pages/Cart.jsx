import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import { api, API_KEYS, getCart, saveCart, clearCart, getSession } from "../api";

export default function Cart() {
  const [cart, setCart] = useState(getCart());
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);
  const navigate = useNavigate();
  const { username } = getSession();

  const removeItem = (i) => {
    const updated = cart.filter((_, idx) => idx !== i);
    setCart(updated);
    saveCart(updated);
  };

  const total = cart.reduce((sum, i) => sum + (i.price || 0), 0);

  const checkout = async () => {
    if (cart.length === 0) return;
    setError("");
    setLoading(true);
    try {
      const orderRes = await api.post(
        "/api/orders",
        { customerId: username, restaurantId: cart[0].restaurantId, items: cart.map((c) => c.name), totalAmount: total },
        { headers: { "X-API-KEY": API_KEYS.order } }
      );
      await api.post(
        "/api/orders/checkout",
        { orderId: orderRes.data.id, amount: total },
        { headers: { "X-API-KEY": API_KEYS.order } }
      );
      clearCart();
      setCart([]);
      navigate("/orders");
    } catch (err) {
      setError(err.response?.data?.error || "Checkout failed.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="page">
      <h1 className="page-title">Your cart</h1>
      <p className="page-subtitle">Review before you send it to the kitchen.</p>

      {cart.length === 0 && <div className="empty-state">Your cart is empty — add something from Restaurants.</div>}

      {cart.map((item, i) => (
        <div className="ticket" key={i}>
          <div className="ticket-main"><strong>{item.name}</strong></div>
          <div style={{ display: "flex", alignItems: "center", gap: 14 }}>
            <span className="price">${item.price?.toFixed(2)}</span>
            <button className="btn btn-ghost btn-small" onClick={() => removeItem(i)}>Remove</button>
          </div>
        </div>
      ))}

      {cart.length > 0 && (
        <>
          <div className="cart-total"><span>Total</span><span>${total.toFixed(2)}</span></div>
          {error && <p className="form-error">{error}</p>}
          <button className="btn btn-primary btn-block" onClick={checkout} disabled={loading} style={{ marginTop: 12 }}>
            {loading ? "Sending order…" : "Checkout"}
          </button>
        </>
      )}
    </div>
  );
}