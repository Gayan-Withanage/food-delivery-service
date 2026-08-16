import React, { useState } from "react";
import { api, saveSession } from "./api";

export default function Login({ onLoggedIn }) {
  const [mode, setMode] = useState("login"); // "login" or "register"
  const [form, setForm] = useState({ username: "", password: "", email: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const update = (field) => (e) => setForm({ ...form, [field]: e.target.value });

  const handleLogin = async (e) => {
    e.preventDefault();
    setError("");
    setLoading(true);
    try {
      const res = await api.post("/auth/login", {
        username: form.username,
        password: form.password,
      });
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
      await api.post("/auth/register", {
        username: form.username,
        password: form.password,
        email: form.email,
        role: "CUSTOMER",
      });
      setMode("login");
      setError("Registered! Now log in with your new account.");
    } catch (err) {
      setError(err.response?.data?.error || "Registration failed.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div style={{ maxWidth: 360, margin: "60px auto", fontFamily: "sans-serif" }}>
      <h2>{mode === "login" ? "Log in" : "Register"}</h2>

      <form onSubmit={mode === "login" ? handleLogin : handleRegister}>
        <div style={{ marginBottom: 12 }}>
          <label>Username</label>
          <input
            style={{ width: "100%", padding: 8 }}
            value={form.username}
            onChange={update("username")}
            required
          />
        </div>

        {mode === "register" && (
          <div style={{ marginBottom: 12 }}>
            <label>Email</label>
            <input
              type="email"
              style={{ width: "100%", padding: 8 }}
              value={form.email}
              onChange={update("email")}
              required
            />
          </div>
        )}

        <div style={{ marginBottom: 12 }}>
          <label>Password</label>
          <input
            type="password"
            style={{ width: "100%", padding: 8 }}
            value={form.password}
            onChange={update("password")}
            required
          />
        </div>

        {error && <p style={{ color: "crimson" }}>{error}</p>}

        <button type="submit" disabled={loading} style={{ width: "100%", padding: 10 }}>
          {loading ? "Please wait..." : mode === "login" ? "Log in" : "Register"}
        </button>
      </form>

      <p style={{ marginTop: 16, textAlign: "center" }}>
        {mode === "login" ? (
          <>
            No account?{" "}
            <a href="#" onClick={() => { setMode("register"); setError(""); }}>
              Register
            </a>
          </>
        ) : (
          <>
            Already have an account?{" "}
            <a href="#" onClick={() => { setMode("login"); setError(""); }}>
              Log in
            </a>
          </>
        )}
      </p>
    </div>
  );
}