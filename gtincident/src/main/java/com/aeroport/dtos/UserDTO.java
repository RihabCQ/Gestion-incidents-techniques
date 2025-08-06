package com.aeroport.dtos;

public class UserDTO {

    private Long id;
    private String nom;
    private String email;
    private String motDePasse; // ✅ ajoute ceci
    private String role;

    // Getters & Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() 
    { return motDePasse; } // ✅ getter
    public void setMotDePasse(String motDePasse) 
    { this.motDePasse = motDePasse; } // ✅ setter
    
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
