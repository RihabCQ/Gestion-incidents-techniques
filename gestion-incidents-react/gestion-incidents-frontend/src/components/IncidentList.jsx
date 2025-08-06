import React, { useEffect, useState } from 'react';
import { getAllIncidents } from '../services/incidentService';

const IncidentList = () => {
  const [incidents, setIncidents] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    fetchIncidents();
  }, []);

  const fetchIncidents = async () => {
    try {
      const data = await getAllIncidents();
      setIncidents(data);
    } catch (error) {
      console.error('Erreur lors du chargement des incidents :', error);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div>
      <h2>Liste des Incidents</h2>
      {loading ? (
        <p>Chargement...</p>
      ) : incidents.length === 0 ? (
        <p>Aucun incident trouvé.</p>
      ) : (
        <ul>
          {incidents.map((incident) => (
            <li key={incident.id}>
              <strong>{incident.titre}</strong> - {incident.description}
            </li>
          ))}
        </ul>
      )}
    </div>
  );
};

export default IncidentList;
