package com.aeroport.controllers;

import com.aeroport.dtos.AuthRequest;
import com.aeroport.dtos.AuthResponse;
import com.aeroport.service.AuthService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")  // Autoriser toutes les origines (ajuster selon besoin)
public class AuthController {
	@Autowired
    private  AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest Request) {
        try {
            AuthResponse authResponse = authService.authenticate(Request);
            return ResponseEntity.ok(authResponse);
        } catch (Exception e) {
            // Ici tu peux logguer l’erreur si besoin
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}