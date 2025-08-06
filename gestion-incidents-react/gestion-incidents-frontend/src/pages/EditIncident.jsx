import React, { useState, useEffect } from "react";
import { useParams, useNavigate } from "react-router-dom";
import { getIncidentById, updateIncident } from "../services/incidentService";
import {
  getCommentairesByIncidentId,
  addCommentaireToIncident,
} from "../services/commentaireService";
import { getAssignmentsByIncidentId } from "../services/assignmentService";

const EditIncident = () => {
  const { id } = useParams();
  const navigate = useNavigate();

  const [incident, setIncident] = useState({
    titre: "",
    description: "",
    statut: "",
  });

  const [commentaires, setCommentaires] = useState([]);
  const [nouveauCommentaire, setNouveauCommentaire] = useState("");
  const [assignments, setAssignments] = useState([]);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const [incidentData, commentairesData, assignmentsData] = await Promise.all([
          getIncidentById(id),
          getCommentairesByIncidentId(id),
          getAssignmentsByIncidentId(id),
        ]);
        setIncident(incidentData);
        setCommentaires(commentairesData);
        setAssignments(assignmentsData);
      } catch (error) {
        console.error("Erreur lors du chargement :", error);
      } finally {
        setLoading(false);
      }
    };

    fetchData();
  }, [id]);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setIncident((prevIncident) => ({
      ...prevIncident,
      [name]: value,
    }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      await updateIncident(id, incident);
      navigate("/incidents");
    } catch (error) {
      console.error("Erreur lors de la mise à jour :", error);
    }
  };

  const handleAddCommentaire = async (e) => {
    e.preventDefault();
    if (!nouveauCommentaire.trim()) return;

    try {
      const newComment = await addCommentaireToIncident({
        contenu: nouveauCommentaire,
        incidentId: id,
      });
      setCommentaires((prev) => [...prev, newComment]);
      setNouveauCommentaire("");
    } catch (error) {
      console.error("Erreur lors de l'ajout du commentaire :", error);
    }
  };

  if (loading) return <p>Chargement...</p>;

  return (
    <div className="max-w-2xl mx-auto p-6 bg-white rounded shadow">
      <h2 className="text-2xl font-bold mb-6 text-blue-700">Modifier l'incident</h2>
      
      {/* Formulaire de mise à jour */}
      <form onSubmit={handleSubmit} className="space-y-4">
        <div>
          <label className="block font-medium">Titre :</label>
          <input
            type="text"
            name="titre"
            value={incident.titre}
            onChange={handleChange}
            required
            className="w-full border border-gray-300 rounded p-2"
          />
        </div>
        <div>
          <label className="block font-medium">Description :</label>
          <textarea
            name="description"
            value={incident.description}
            onChange={handleChange}
            required
            className="w-full border border-gray-300 rounded p-2"
          />
        </div>
        <div>
          <label className="block font-medium">Statut :</label>
          <select
            name="statut"
            value={incident.statut}
            onChange={handleChange}
            required
            className="w-full border border-gray-300 rounded p-2"
          >
            <option value="">-- Choisir un statut --</option>
            <option value="EN_COURS">En cours</option>
            <option value="RESOLU">Résolu</option>
            <option value="EN_ATTENTE">En attente</option>
          </select>
        </div>
        <button
          type="submit"
          className="bg-blue-600 text-white px-4 py-2 rounded hover:bg-blue-700"
        >
          Mettre à jour
        </button>
      </form>

      {/* Section des commentaires */}
      <div className="mt-10">
        <h3 className="text-xl font-semibold mb-2">Commentaires</h3>
        {commentaires.length === 0 ? (
          <p className="text-gray-500">Aucun commentaire pour cet incident.</p>
        ) : (
          <ul className="space-y-2">
            {commentaires.map((commentaire) => (
              <li key={commentaire.id} className="border p-2 rounded bg-gray-50">
                {commentaire.contenu}
              </li>
            ))}
          </ul>
        )}

        <form onSubmit={handleAddCommentaire} className="mt-4">
          <textarea
            value={nouveauCommentaire}
            onChange={(e) => setNouveauCommentaire(e.target.value)}
            placeholder="Ajouter un commentaire..."
            className="w-full border border-gray-300 rounded p-2"
          />
          <button
            type="submit"
            className="mt-2 bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700"
          >
            Ajouter
          </button>
        </form>
      </div>

      {/* Section des affectations */}
      <div className="mt-10">
        <h3 className="text-xl font-semibold mb-2 text-indigo-700">Techniciens affectés</h3>
        {assignments.length === 0 ? (
          <p className="text-gray-500">Aucun technicien affecté à cet incident.</p>
        ) : (
          <ul className="space-y-2">
            {assignments.map((a) => (
              <li key={a.id} className="border p-2 rounded bg-gray-100">
                <strong>{a.technicien?.nom} {a.technicien?.prenom}</strong> – Affecté le{" "}
                {new Date(a.dateAffectation).toLocaleString()}
              </li>
            ))}
          </ul>
        )}
      </div>
    </div>
  );
};

export default EditIncident;
