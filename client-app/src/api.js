import axios from "axios";

const GATEWAY_URL = "http://localhost:8080";

export const api = axios.create({ baseURL: GATEWAY_URL });

// Attach the JWT automatically to every request once the user has logged in
api.interceptors.request.use((config) => {
  const token = localStorage.getItem("token");
  if (token) {
    config.headers.Authorization = `Bearer ${token}`;
  }
  return config;
});

export function saveSession(token, role, username) {
  localStorage.setItem("token", token);
  localStorage.setItem("role", role);
  localStorage.setItem("username", username);
}

export function clearSession() {
  localStorage.removeItem("token");
  localStorage.removeItem("role");
  localStorage.removeItem("username");
}

export function getSession() {
  return {
    token: localStorage.getItem("token"),
    role: localStorage.getItem("role"),
    username: localStorage.getItem("username"),
  };
}