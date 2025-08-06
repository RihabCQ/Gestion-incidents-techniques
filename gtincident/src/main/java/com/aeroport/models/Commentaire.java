package com.aeroport.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "commentaires")
public class Commentaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 2000)
    private String contenu;

    private LocalDateTime dateCommentaire;

    @ManyToOne
    @JoinColumn(name = "incident_id")
    private Incident incident;

    @ManyToOne
    @JoinColumn(name = "auteur_id", nullable = false)
    private User auteur;

    // Getters et setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public LocalDateTime getDateCommentaire() { return dateCommentaire; }
    public void setDateCommentaire(LocalDateTime dateCommentaire) { this.dateCommentaire = dateCommentaire; }

    public Incident getIncident() { return incident; }
    public void setIncident(Incident incident) { this.incident = incident; }

    public User getAuteur() { return auteur; }
    public void setAuteur(User auteur) { this.auteur = auteur; }
}

