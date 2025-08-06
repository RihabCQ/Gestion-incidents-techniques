package com.aeroport.service;

import com.aeroport.dtos.AuthRequest;
import com.aeroport.dtos.AuthResponse;
import com.aeroport.security.CustomUserDetailsService;
import com.aeroport.security.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse authenticate(AuthRequest request) {
        // Authentifie avec Spring Security
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(request.getEmail(), request.getMotDePasse())
        );

        // Charge l'utilisateur et génère le token JWT
        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtUtil.generateToken(user);

        return new AuthResponse(token);
    }
}