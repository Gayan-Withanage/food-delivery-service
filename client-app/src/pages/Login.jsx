import React, { useState } from "react";
import { api, saveSession, API_KEYS } from "../api";

export default function Login({ onLoggedIn }) {
  const [mode, setMode] = useState("login");
  const [form, setForm] = useState({ username: "", password: "", email: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const update = (field) => (e) => setForm({ ...form, [field]: e.target.value });

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      const res = await api.post(
        "/auth/login",
        { username: form.username, password: form.password },
        { headers: { "X-API-KEY": API_KEYS.auth } }
      );
      saveSession(res.data.token, res.data.role, form.username);
      onLoggedIn();
    } catch (err) {
      setError(err.response?.data?.error || "Login failed. Check your username and password.");
    } finally {
      setLoading(false);
    }
  };

  const handleRegister = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      await api.post(
        "/auth/register",
        { username: form.username, password: form.password, email: form.email, role: "CUSTOMER" },
        { headers: { "X-API-KEY": API_KEYS.auth } }
      );
      setMode("login");
      setError("");
    } catch (err) {
      setError(err.response?.data?.error || "Registration failed.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="form-card">
      <h2>{mode === "login" ? "Log in" : "Create account"}</h2>
      <p className="page-subtitle" style={{ marginBottom: 20 }}>
        {mode === "login" ? "Welcome back — order's waiting." : "Takes about ten seconds."}
      </p>

      <form onSubmit={mode === "login" ? handleLogin : handleRegister}>
        <div className="form-group">
          <label>Username</label>
          <input value={form.username} onChange={update("username")} required />
        </div>

        {mode === "register" && (
          <div className="form-group">
            <label>Email</label>
            <input type="email" value={form.email} onChange={update("email")} required />
          </div>
        )}

        <div className="form-group">
          <label>Password</label>
          <input type="password" value={form.password} onChange={update("password")} required />
        </div>

        {error && <p className="form-error">{error}</p>}
        {!error && mode === "login" && form.username === "" && null}

        <button type="submit" className="btn btn-primary btn-block" disabled={loading}>
          {loading ? "Please wait…" : mode === "login" ? "Log in" : "Register"}
        </button>
      </form>

      <p className="form-switch">
        {mode === "login" ? (
          <>No account? <a href="#" onClick={(e) => { e.preventDefault(); setMode("register"); setError(""); }}>Register</a></>
        ) : (
          <>Already have one? <a href="#" onClick={(e) => { e.preventDefault(); setMode("login"); setError(""); }}>Log in</a></>
        )}
      </p>
    </div>
  );
}