// src/components/Navbar.jsx
import { Link, useLocation } from "react-router-dom";

const Navbar = () => {
  const location = useLocation();

  const linkClass = (path) =>
    `px-4 py-2 rounded hover:bg-blue-600 hover:text-white ${
      location.pathname === path ? "bg-blue-600 text-white" : "text-gray-700"
    }`;

  return (
    <nav className="bg-white shadow-md p-4 flex justify-between items-center">
      <div className="text-2xl font-bold text-blue-600">Gestion Incidents</div>
      <div className="space-x-4">
        <Link to="/" className={linkClass("/")}>
          Accueil
        </Link>
        <Link to="/login" className={linkClass("/login")}>
          Connexion
        </Link>
        <Link to="/about" className={linkClass("/about")}>
          À propos
        </Link>
        <Link to="/incidents" className={linkClass("/incidents")}>
          Incidents
        </Link>
      </div>
    </nav>
  );
};

export default Navbar;
