package com.aeroport.dtos;

public class AuthResponse {
    private String token;

    // Constructeurs

    public AuthResponse(String token) {
        this.token = token;
    }

    // Getter & Setter
    public String getToken() {
        return token;
    }

   
}
