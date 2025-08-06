package com.aeroport.mappers;

import com.aeroport.dtos.CommentaireDTO;
import com.aeroport.models.Commentaire;
import com.aeroport.models.Incident;
import com.aeroport.models.User;

public class CommentaireMapper {

    public static CommentaireDTO toDTO(Commentaire commentaire) {
        CommentaireDTO dto = new CommentaireDTO();
        dto.setId(commentaire.getId());
        dto.setContenu(commentaire.getContenu());
        dto.setDateCreation(commentaire.getDateCommentaire());

        if (commentaire.getIncident() != null) {
            dto.setIncidentId(commentaire.getIncident().getId());
        }

        if (commentaire.getAuteur() != null) {
            dto.setAuteurId(commentaire.getAuteur().getId());
        }

        return dto;
    }

    public static Commentaire toEntity(CommentaireDTO dto, Incident incident, User auteur) {
        Commentaire commentaire = new Commentaire();
        commentaire.setId(dto.getId()); // null pour un POST
        commentaire.setContenu(dto.getContenu());
        commentaire.setDateCommentaire(dto.getDateCreation()); // sera forcée à `now()` dans le controller
        commentaire.setIncident(incident);
        commentaire.setAuteur(auteur);
        return commentaire;
    }
}
