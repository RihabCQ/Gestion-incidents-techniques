import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { createIncident } from '../services/incidentService';

const AjouterIncident = () => {
  const navigate = useNavigate();
  const [formData, setFormData] = useState({
    titre: '',
    description: '',
    statut: 'EN_COURS',
  });

  const handleChange = (e) => {
    const { name, value } = e.target;
    setFormData((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await createIncident(formData);
      navigate('/incidents');
    } catch (err) {
      console.error('Erreur lors de l’ajout :', err);
    }
  };

  return (
    <div className="max-w-2xl mx-auto mt-10 px-6 py-8 bg-white rounded shadow">
      <h2 className="text-2xl font-bold text-blue-700 mb-6 text-center">Ajouter un incident</h2>

      <form onSubmit={handleSubmit} className="space-y-5">
        <div>
          <label className="block mb-1 font-medium text-gray-700">Titre</label>
          <input
            type="text"
            name="titre"
            value={formData.titre}
            onChange={handleChange}
            className="w-full border border-gray-300 px-4 py-2 rounded focus:ring focus:ring-blue-200"
            required
          />
        </div>

        <div>
          <label className="block mb-1 font-medium text-gray-700">Description</label>
          <textarea
            name="description"
            value={formData.description}
            onChange={handleChange}
            rows="4"
            className="w-full border border-gray-300 px-4 py-2 rounded focus:ring focus:ring-blue-200"
            required
          />
        </div>

        <div>
          <label className="block mb-1 font-medium text-gray-700">Statut</label>
          <select
            name="statut"
            value={formData.statut}
            onChange={handleChange}
            className="w-full border border-gray-300 px-4 py-2 rounded focus:ring focus:ring-blue-200"
          >
            <option value="EN_COURS">En cours</option>
            <option value="RESOLU">Résolu</option>
            <option value="EN_ATTENTE">En attente</option>
          </select>
        </div>

        <div className="text-center">
          <button
            type="submit"
            className="bg-blue-600 hover:bg-blue-700 text-white px-6 py-2 rounded font-semibold transition-colors"
          >
            Ajouter
          </button>
        </div>
      </form>
    </div>
  );
};

export default AjouterIncident;