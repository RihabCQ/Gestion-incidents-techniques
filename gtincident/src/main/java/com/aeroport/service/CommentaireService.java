package com.aeroport.service;

import com.aeroport.exceptions.ResourceNotFoundException;
import com.aeroport.models.Commentaire;
import com.aeroport.models.User;
import com.aeroport.repositories.CommentaireRepository;
import com.aeroport.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentaireService {

    private final CommentaireRepository commentaireRepository;
    private final UserRepository userRepository;

    @Autowired
    public CommentaireService(CommentaireRepository commentaireRepository, UserRepository userRepository) {
        this.commentaireRepository = commentaireRepository;
        this.userRepository = userRepository;
    }

    public List<Commentaire> getAllCommentaires() {
        return commentaireRepository.findAll();
    }

    public Commentaire getCommentaireById(Long id) {
        return commentaireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commentaire non trouvé avec l'id : " + id));
    }

    public Commentaire saveCommentaire(Commentaire commentaire) {
        // Récupère l'utilisateur connecté
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        User auteur = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        commentaire.setAuteur(auteur); // Associe l’auteur avant la sauvegarde
        return commentaireRepository.save(commentaire);
    }

    public void deleteCommentaire(Long id) {
        commentaireRepository.deleteById(id);
    }

    public Commentaire updateCommentaire(Long id, Commentaire updatedCommentaire) {
        Commentaire existingCommentaire = commentaireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Commentaire non trouvé avec l'id : " + id));

        existingCommentaire.setContenu(updatedCommentaire.getContenu());
        existingCommentaire.setDateCommentaire(updatedCommentaire.getDateCommentaire());
        // Tu peux ajouter d'autres champs à mettre à jour ici

        return commentaireRepository.save(existingCommentaire);
    }
    
}
