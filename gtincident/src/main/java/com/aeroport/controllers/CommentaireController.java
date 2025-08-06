package com.aeroport.controllers;

import com.aeroport.dtos.CommentaireDTO;
import com.aeroport.exceptions.ResourceNotFoundException;
import com.aeroport.mappers.CommentaireMapper;
import com.aeroport.models.Commentaire;
import com.aeroport.models.Incident;
import com.aeroport.models.User;
import com.aeroport.repositories.IncidentRepository;
import com.aeroport.repositories.UserRepository;
import com.aeroport.service.CommentaireService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/commentaires")
public class CommentaireController {

    @Autowired
    private CommentaireService commentaireService;

    @Autowired
    private IncidentRepository incidentRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<CommentaireDTO> getAllCommentaires() {
        return commentaireService.getAllCommentaires().stream()
                .map(CommentaireMapper::toDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<CommentaireDTO> createCommentaire(
            @RequestBody CommentaireDTO dto,
            Authentication authentication) {

        // 1. Récupérer l'incident ciblé
        Incident incident = incidentRepository.findById(dto.getIncidentId())
                .orElseThrow(() -> new ResourceNotFoundException("Incident non trouvé avec l'ID : " + dto.getIncidentId()));

        // 2. Identifier l'auteur connecté via le JWT
        String email = authentication.getName(); // JWT contient le nom d'utilisateur (souvent email)
        User auteur = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur avec l'email " + email + " non trouvé."));

        // 3. Mapper vers l'entité + compléter les champs
        Commentaire commentaire = CommentaireMapper.toEntity(dto, incident, auteur);
        commentaire.setDateCommentaire(LocalDateTime.now());

        // 4. Enregistrer et retourner le DTO
        Commentaire saved = commentaireService.saveCommentaire(commentaire);
        return new ResponseEntity<>(CommentaireMapper.toDTO(saved), HttpStatus.CREATED);
    }
@PutMapping("/{id}")
public ResponseEntity<CommentaireDTO> updateCommentaire(
        @PathVariable Long id,
        @RequestBody CommentaireDTO dto,
        Authentication authentication) {

    // Sécurité : vérifier que le commentaire appartient à l'utilisateur
    Commentaire existing = commentaireService.getCommentaireById(id);

    String email = authentication.getName();
    if (!existing.getAuteur().getEmail().equals(email)) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    // Mise à jour du contenu
    existing.setContenu(dto.getContenu());
    existing.setDateCommentaire(LocalDateTime.now()); // facultatif

    Commentaire updated = commentaireService.saveCommentaire(existing);
    return ResponseEntity.ok(CommentaireMapper.toDTO(updated));
}@DeleteMapping("/{id}")
public ResponseEntity<Void> deleteCommentaire(@PathVariable Long id, Authentication authentication) {
    // Vérification que l'utilisateur connecté est bien l'auteur du commentaire ou a les droits
    Commentaire commentaire = commentaireService.getCommentaireById(id);

    String email = authentication.getName();
    User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé"));

    // Autorisation : seul l'auteur OU un admin peut supprimer
    if (!commentaire.getAuteur().getId().equals(user.getId()) && !user.getRole().equals("ADMIN")) {
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    commentaireService.deleteCommentaire(id);
    return new ResponseEntity<>(HttpStatus.NO_CONTENT);
}

}

