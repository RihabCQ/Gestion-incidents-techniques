import React, { useEffect, useState } from 'react';
import { getAllIncidents, deleteIncidentById } from '../services/incidentService';
import { useNavigate } from 'react-router-dom';
import { motion } from 'framer-motion';

const Incidents = () => {
  const [incidents, setIncidents] = useState([]);
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  const fetchIncidents = async () => {
    try {
      const data = await getAllIncidents();
      setIncidents(data);
    } catch (error) {
      console.error("Erreur lors du chargement des incidents :", error);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchIncidents();
  }, []);

  const handleDelete = async (id) => {
    try {
      await deleteIncidentById(id);
      fetchIncidents();
    } catch (error) {
      console.error("Erreur lors de la suppression :", error);
    }
  };

  const renderBadge = (statut) => {
    const base = "px-3 py-1 rounded-full text-sm font-semibold";
    switch (statut) {
      case "EN_COURS":
        return <span className={`${base} bg-yellow-100 text-yellow-800`}>En cours</span>;
      case "RESOLU":
        return <span className={`${base} bg-green-100 text-green-800`}>Résolu</span>;
      case "EN_ATTENTE":
        return <span className={`${base} bg-orange-100 text-orange-800`}>En attente</span>;
      default:
        return <span className={`${base} bg-gray-200 text-gray-700`}>{statut}</span>;
    }
  };

  if (loading) {
    return <p className="text-center text-gray-500 mt-10">Chargement des incidents...</p>;
  }

  return (
    <div className="max-w-6xl mx-auto p-6">
      <h1 className="text-3xl font-bold text-blue-800 mb-8 text-center">Liste des Incidents</h1>

      {incidents.length === 0 ? (
        <p className="text-gray-600 text-center">Aucun incident trouvé.</p>
      ) : (
        <div className="grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
          {incidents.map((incident) => (
            <motion.div
              key={incident.id}
              className="bg-white border border-gray-200 rounded-xl p-5 shadow-md hover:shadow-lg transition"
              whileHover={{ scale: 1.02 }}
            >
              <h2 className="text-xl font-bold text-blue-700 mb-2">{incident.titre}</h2>
              <p className="text-gray-700 mb-3">{incident.description}</p>
              <div className="mb-4">{renderBadge(incident.statut)}</div>
              <div className="flex justify-between">
                <button
                  onClick={() => navigate(`/incidents/edit/${incident.id}`)}
                  className="bg-blue-600 hover:bg-blue-700 text-white text-sm px-4 py-2 rounded-lg"
                >
                  Modifier
                </button>
                <button
                  onClick={() => handleDelete(incident.id)}
                  className="bg-red-600 hover:bg-red-700 text-white text-sm px-4 py-2 rounded-lg"
                >
                  Supprimer
                </button>
              </div>
            </motion.div>
          ))}
        </div>
      )}
    </div>
  );
};

export default Incidents;
