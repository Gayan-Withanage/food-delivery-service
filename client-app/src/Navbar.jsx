import React from "react";
import { NavLink, useNavigate } from "react-router-dom";
import { clearSession, getSession } from "./api";

export default function Navbar() {
  const navigate = useNavigate();
  const { username } = getSession();

  const linkClass = ({ isActive }) => "navbar-link" + (isActive ? " active" : "");

  const logout = () => {
    clearSession();
    navigate("/login");
  };

  return (
    <nav className="navbar">
      <div className="navbar-left">
        <span className="navbar-brand">Ticket &amp; Table</span>
        <div className="navbar-links">
          <NavLink to="/restaurants" className={linkClass}>Restaurants</NavLink>
          <NavLink to="/cart" className={linkClass}>Cart</NavLink>
          <NavLink to="/orders" className={linkClass}>Orders</NavLink>
          <NavLink to="/payments" className={linkClass}>Payments</NavLink>
          <NavLink to="/notifications" className={linkClass}>Notifications</NavLink>
        </div>
      </div>
      <div>
        <span className="navbar-user">Hi, <b>{username}</b></span>
        <button className="btn btn-ghost btn-small" onClick={logout} style={{ color: "#fff", borderColor: "#4a4638" }}>
          Log out
        </button>
      </div>
    </nav>
  );
}