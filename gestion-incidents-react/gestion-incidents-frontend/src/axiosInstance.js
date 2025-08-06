// src/axiosInstance.js
import axios from "axios";

// Crée une instance Axios
const axiosInstance = axios.create({
  baseURL: "http://localhost:8083/api", // adapte si besoin
});

// Intercepteur pour ajouter le token à chaque requête
axiosInstance.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("token"); // on récupère le token
    if (token) {
      config.headers.Authorization = `Bearer ${token}`; // ajout dans les headers
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axiosInstance;
