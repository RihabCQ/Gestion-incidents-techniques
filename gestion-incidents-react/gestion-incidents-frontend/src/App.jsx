// src/App.jsx
import React from "react";
import { Routes, Route } from "react-router-dom";
import Navbar from "./components/Navbar";

// Pages
import Home from "./pages/Home";
import Login from "./pages/Login_temp";
import About from "./pages/About";
import Incidents from "./pages/Incidents";
import AjouterIncident from "./pages/AjouterIncident"; // ✅ corriger le nom
import EditIncident from "./pages/EditIncident";

function App() {
  return (
    <>
      <Navbar />
      <div className="p-4">
        <Routes>
          <Route path="/" element={<Home />} />
          <Route path="/login" element={<Login />} />
          <Route path="/about" element={<About />} />
          <Route path="/incidents" element={<Incidents />} />
          <Route path="/ajouter-incident" element={<AjouterIncident />} />
          <Route path="/incidents/edit/:id" element={<EditIncident />} />
        </Routes>
      </div>
    </>
  );
}

export default App;
