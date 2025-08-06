package com.aeroport.mappers;

import com.aeroport.dtos.IncidentDTO;
import com.aeroport.models.Incident;

public class IncidentMapper {

    public static IncidentDTO toDTO(Incident incident) {
        IncidentDTO dto = new IncidentDTO();
        dto.setId(incident.getId());
        dto.setTitre(incident.getTitre());
        dto.setDescription(incident.getDescription());
        dto.setDateDeclaration(incident.getDateDeclaration());
        dto.setStatut(incident.getStatut());
        return dto;
    }

    public static Incident toEntity(IncidentDTO dto) {
        Incident incident = new Incident();
        incident.setId(dto.getId());
        incident.setTitre(dto.getTitre());
        incident.setDescription(dto.getDescription());
        incident.setDateDeclaration(dto.getDateDeclaration());
        incident.setStatut(dto.getStatut());
        return incident;
    }
}
