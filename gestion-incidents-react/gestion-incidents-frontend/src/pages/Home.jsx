import { motion } from "framer-motion";
import { useNavigate } from "react-router-dom";

export default function Home() {
  const navigate = useNavigate();

  const handleAjouterIncident = () => {
    navigate("/ajouter-incident");
  };

  return (
    <div className="min-h-screen flex flex-col bg-gradient-to-br from-blue-50 to-blue-100 text-blue-900">
      {/* Hero Section */}
      <section className="flex flex-col justify-center items-center flex-grow text-center px-6 py-20">
        <motion.h1
          className="text-5xl md:text-6xl font-extrabold leading-tight mb-6 text-blue-800"
          initial={{ opacity: 0, y: -40 }}
          animate={{ opacity: 1, y: 0 }}
          transition={{ duration: 0.8 }}
        >
          Plateforme de gestion des incidents
        </motion.h1>

        <motion.p
          className="text-lg md:text-xl text-blue-700 mb-8 max-w-2xl"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.4, duration: 0.6 }}
        >
          Suivez, traitez et résolvez les incidents en toute simplicité, avec une interface moderne et fluide.
        </motion.p>

        <motion.div
          className="flex flex-col md:flex-row items-center gap-4"
          initial={{ opacity: 0 }}
          animate={{ opacity: 1 }}
          transition={{ delay: 0.6 }}
        >
          <button
            onClick={handleAjouterIncident}
            className="bg-green-600 hover:bg-green-700 text-white font-semibold py-3 px-6 rounded-lg shadow transition duration-300"
          >
            Ajouter un incident
          </button>

          <a
            href="#apropos"
            className="bg-blue-600 hover:bg-blue-700 text-white font-semibold py-3 px-6 rounded-lg shadow transition duration-300"
          >
            En savoir plus
          </a>
        </motion.div>
      </section>

      {/* À propos */}
      <section id="apropos" className="bg-white py-20 px-6 text-center">
        <div className="max-w-4xl mx-auto">
          <h2 className="text-3xl font-bold text-blue-700 mb-4">À propos</h2>
          <p className="text-blue-800 text-lg leading-relaxed">
            Cette application vous permet de gérer efficacement les incidents techniques. 
            Elle est conçue pour offrir un suivi clair, une interface conviviale et une meilleure réactivité.
          </p>
        </div>
      </section>

      {/* Contact */}
      <section id="contact" className="bg-blue-50 py-20 px-6 text-center">
        <div className="max-w-4xl mx-auto">
          <h2 className="text-3xl font-bold text-blue-700 mb-4">Contact</h2>
          <p className="text-blue-800 text-lg leading-relaxed">
            Pour toute question ou suggestion, contactez-nous à : <br />
            <span className="font-semibold">support@gtincident.com</span>
          </p>
        </div>
      </section>

      {/* Footer */}
      <footer className="bg-white py-6 text-center text-blue-600 text-sm">
        &copy; 2025 Gestion des incidents. Tous droits réservés.
      </footer>
    </div>
  );
}