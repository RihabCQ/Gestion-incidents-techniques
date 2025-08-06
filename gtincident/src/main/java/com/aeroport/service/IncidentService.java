package com.aeroport.service;

import com.aeroport.exceptions.ResourceNotFoundException;
import com.aeroport.models.Incident;
import com.aeroport.repositories.IncidentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncidentService {

    private final IncidentRepository incidentRepository;

    @Autowired
    public IncidentService(IncidentRepository incidentRepository) {
        this.incidentRepository = incidentRepository;
    }

    public List<Incident> getAllIncidents() {
        return incidentRepository.findAll();
    }

    public Incident getIncidentById(Long id) {
        return incidentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incident non trouvé avec l'id : " + id));
    }

    public Incident saveIncident(Incident incident) {
        return incidentRepository.save(incident);
    }

    public void deleteIncident(Long id) {
        incidentRepository.deleteById(id);
    }
    public Incident updateIncident(Long id, Incident updatedIncident) {
        Incident existingIncident = incidentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Incident non trouvé avec l'id : " + id));

        existingIncident.setTitre(updatedIncident.getTitre());
        existingIncident.setDescription(updatedIncident.getDescription());
        existingIncident.setDateDeclaration(updatedIncident.getDateDeclaration());
        existingIncident.setStatut(updatedIncident.getStatut());  // <-- ici

        return incidentRepository.save(existingIncident);
    }


}
