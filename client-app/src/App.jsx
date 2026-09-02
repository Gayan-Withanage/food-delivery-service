import React from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import Login from "./pages/Login";
import Restaurants from "./pages/Restaurants";
import Cart from "./pages/Cart";
import Orders from "./pages/Orders";
import Payments from "./pages/Payments";
import Notifications from "./pages/Notifications";
import Navbar from "./Navbar";
import { getSession } from "./api";

function Protected({ children }) {
  return getSession().token ? children : <Navigate to="/login" />;
}

export default function App() {
  return (
    <BrowserRouter>
      <ProtectedLayout />
    </BrowserRouter>
  );
}

function ProtectedLayout() {
  const loggedIn = !!getSession().token;
  return (
    <>
      {loggedIn && <Navbar />}
      <Routes>
        <Route path="/login" element={<Login onLoggedIn={() => window.location.assign("/restaurants")} />} />
        <Route path="/restaurants" element={<Protected><Restaurants /></Protected>} />
        <Route path="/cart" element={<Protected><Cart /></Protected>} />
        <Route path="/orders" element={<Protected><Orders /></Protected>} />
        <Route path="/payments" element={<Protected><Payments /></Protected>} />
        <Route path="/notifications" element={<Protected><Notifications /></Protected>} />
        <Route path="*" element={<Navigate to={loggedIn ? "/restaurants" : "/login"} />} />
      </Routes>
    </>
  );
}