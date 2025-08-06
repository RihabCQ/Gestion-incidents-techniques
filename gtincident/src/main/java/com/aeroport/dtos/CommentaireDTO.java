package com.aeroport.dtos;

import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

public class CommentaireDTO {

    private Long id;
    private String contenu;
    private LocalDateTime dateCreation;
    private Long incidentId;  // référence à l'incident
    @JsonProperty(access = Access.READ_ONLY)
    private Long auteurId; // Seulement pour lecture (pas transmis en création)

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public LocalDateTime getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(LocalDateTime dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Long getIncidentId() {
        return incidentId;
    }

    public void setIncidentId(Long incidentId) {
        this.incidentId = incidentId;
    }

    public Long getAuteurId() {
        return auteurId;
    }
    public void setAuteurId(Long auteurId) {
        this.auteurId = auteurId;
    }

}
