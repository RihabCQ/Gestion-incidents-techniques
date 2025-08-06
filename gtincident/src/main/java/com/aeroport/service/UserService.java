package com.aeroport.service;

import com.aeroport.exceptions.ResourceNotFoundException;
import com.aeroport.models.User;
import com.aeroport.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id : " + id));
    }

    public User saveUser(User user) {
    	  if (user.getMotDePasse() != null) {
    	        user.setMotDePasse(passwordEncoder.encode(user.getMotDePasse()));
    	    }
    	  return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(Long id, User updatedUser) {
        User existingUser = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Utilisateur non trouvé avec l'id : " + id));

        existingUser.setNom(updatedUser.getNom());
        existingUser.setEmail(updatedUser.getEmail());

        // uniquement si un nouveau mot de passe est fourni
        if (updatedUser.getMotDePasse() != null && !updatedUser.getMotDePasse().isBlank()) {
            existingUser.setMotDePasse(passwordEncoder.encode(updatedUser.getMotDePasse()));
        }

        existingUser.setRole(updatedUser.getRole());

        return userRepository.save(existingUser);
    }
}
