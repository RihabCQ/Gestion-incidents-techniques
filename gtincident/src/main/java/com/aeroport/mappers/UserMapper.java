package com.aeroport.mappers;

import com.aeroport.dtos.UserDTO;
import com.aeroport.models.User;

public class UserMapper {

    public static UserDTO toDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setNom(user.getNom());
        dto.setEmail(user.getEmail());
        dto.setMotDePasse(user.getMotDePasse()); // ✅ important si tu veux voir le champ (ou omettre si confidentiel)
        dto.setRole(user.getRole());
        return dto;
    }

    public static User toEntity(UserDTO dto) {
        User user = new User();
        user.setId(dto.getId());
        user.setNom(dto.getNom());
        user.setEmail(dto.getEmail());
        user.setMotDePasse(dto.getMotDePasse()); // ⚠️ Très important
        user.setRole(dto.getRole());
        return user;
    }
}
