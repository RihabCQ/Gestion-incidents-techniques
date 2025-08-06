import axios from "axios";

const API_URL = "http://localhost:8083/api/assignments";

// Récupérer les affectations d’un incident
export const getAssignmentsByIncidentId = async (incidentId) => {
  try {
    const response = await axios.get(`${API_URL}/incident/${incidentId}`);
    return response.data;
  } catch (error) {
    console.error("Erreur lors du chargement des affectations :", error);
    return [];
  }
};
