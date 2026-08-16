import React, { useState } from "react";
import Login from "./Login";
import Restaurants from "./Restaurants";
import { getSession } from "./api";

export default function App() {
  const [loggedIn, setLoggedIn] = useState(!!getSession().token);

  return loggedIn ? (
    <Restaurants onLoggedOut={() => setLoggedIn(false)} />
  ) : (
    <Login onLoggedIn={() => setLoggedIn(true)} />
  );
}