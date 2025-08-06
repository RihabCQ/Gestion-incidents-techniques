// src/services/incidentService.js
import axios from 'axios';

const API_URL = 'http://localhost:8083/api/incidents';

// 🔐 Génère l'en-tête Authorization avec le token JWT
const authHeader = () => {
  const token = localStorage.getItem("token");
  return {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  };
};

// 🔹 Récupérer tous les incidents
export const getAllIncidents = async () => {
  const response = await axios.get(API_URL, authHeader());
  return response.data;
};

// 🔹 Récupérer un incident par ID
export const getIncidentById = async (id) => {
  const response = await axios.get(`${API_URL}/${id}`, authHeader());
  return response.data;
};

// 🔹 Créer un nouvel incident
export const createIncident = async (incidentData) => {
  const response = await axios.post(API_URL, incidentData, authHeader());
  return response.data;
};

// 🔹 Modifier un incident
export const updateIncident = async (id, updatedIncident) => {
  const response = await axios.put(`${API_URL}/${id}`, updatedIncident, authHeader());
  return response.data;
};

// 🔹 Supprimer un incident
export const deleteIncidentById = async (id) => {
  const response = await axios.delete(`${API_URL}/${id}`, authHeader());
  return response.data;
};
