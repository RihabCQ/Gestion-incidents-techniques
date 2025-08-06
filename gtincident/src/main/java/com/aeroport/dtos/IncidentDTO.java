package com.aeroport.dtos;

import java.time.LocalDateTime;

public class IncidentDTO {

    private Long id;
    private String titre;
    private String description;
    private LocalDateTime dateDeclaration;
    private String statut;

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateDeclaration() {
        return dateDeclaration;
    }

    public void setDateDeclaration(LocalDateTime dateDeclaration) {
        this.dateDeclaration = dateDeclaration;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }
}
