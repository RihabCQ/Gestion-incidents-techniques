import axios from "axios";

const API_URL = "http://localhost:8083/api/commentaires";

// 🔹 Récupère les commentaires liés à un incident
export const getCommentairesByIncidentId = async (incidentId) => {
  try {
    const response = await axios.get(`${API_URL}/incident/${incidentId}`);
    return response.data;
  } catch (error) {
    console.error("Erreur lors du chargement des commentaires :", error);
    return [];
  }
};

// 🔹 Crée un nouveau commentaire pour un incident
export const addCommentaireToIncident = async ({ contenu, incidentId }) => {
  try {
    const response = await axios.post(`${API_URL}`, {
      contenu,
      incidentId, // Le backend attend incidentId dans le body
    });
    return response.data;
  } catch (error) {
    console.error("Erreur lors de l'ajout du commentaire :", error);
    throw error;
  }
};
