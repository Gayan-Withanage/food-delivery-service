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

// One API key per service — replace these with your own generated keys,
// matching each service's application.yml exactly
export const API_KEYS = {
  auth: "70b6ccf48d0f26e0543104d3738e4dbe",
  restaurant: "5d9ed270ef016a016b30027645689ffe",
  order: "70f5b2a758bbc21962080b0b281934f6",
  delivery: "ab9e48196b29810d80991601bb850d33",
  notification: "7f30596e43ac7f5e9b902c7728dffbe5",
  payment: "8e5ca88f08921358127efe705aa1cd25",
};

export function saveSession(token, role, username) {
  localStorage.setItem("token", token);
  localStorage.setItem("role", role);
  localStorage.setItem("username", username);
}

export function clearSession() {
  localStorage.removeItem("token");
  localStorage.removeItem("role");
  localStorage.removeItem("username");
  localStorage.removeItem("cart");
}

export function getSession() {
  return {
    token: localStorage.getItem("token"),
    role: localStorage.getItem("role"),
    username: localStorage.getItem("username"),
  };
}

// Simple cart stored in localStorage — cleared on logout or after checkout
export function getCart() {
  return JSON.parse(localStorage.getItem("cart") || "[]");
}
export function saveCart(cart) {
  localStorage.setItem("cart", JSON.stringify(cart));
}
export function addToCart(item) {
  const cart = getCart();
  cart.push(item);
  saveCart(cart);
  return cart;
}
export function clearCart() {
  localStorage.removeItem("cart");
}