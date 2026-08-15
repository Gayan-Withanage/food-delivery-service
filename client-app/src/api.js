import axios from "axios";

// Everything goes through the API Gateway — never call a microservice directly.
const GATEWAY_URL = "http://localhost:8080";

export const api = axios.create({ baseURL: GATEWAY_URL });

// TODO: after login, attach the JWT to every request:
// api.interceptors.request.use((config) => {
//   const token = localStorage.getItem("token");
//   if (token) config.headers.Authorization = `Bearer ${token}`;
//   return config;
// });
