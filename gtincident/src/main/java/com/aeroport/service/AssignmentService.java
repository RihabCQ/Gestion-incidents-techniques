package com.aeroport.service;

import com.aeroport.exceptions.ResourceNotFoundException;
import com.aeroport.models.Assignment;
import com.aeroport.models.Incident;
import com.aeroport.models.User;
import com.aeroport.repositories.AssignmentRepository;
import com.aeroport.repositories.IncidentRepository;
import com.aeroport.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AssignmentService {

    private final AssignmentRepository assignmentRepository;
    private final IncidentRepository incidentRepository;
    private final UserRepository userRepository;

    @Autowired
    public AssignmentService(AssignmentRepository assignmentRepository,
                             IncidentRepository incidentRepository,
                             UserRepository userRepository) {
        this.assignmentRepository = assignmentRepository;
        this.incidentRepository = incidentRepository;
        this.userRepository = userRepository;
    }

    public List<Assignment> getAllAssignments() {
        return assignmentRepository.findAll();
    }

    public Assignment getAssignmentById(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Assignment non trouvé avec l'id : " + id));
    }

    public Assignment createAssignment(Long incidentId, Long technicienId) {
        Incident incident = incidentRepository.findById(incidentId)
                .orElseThrow(() -> new ResourceNotFoundException("Incident non trouvé avec l'id : " + incidentId));

        User technicien = userRepository.findById(technicienId)
                .orElseThrow(() -> new ResourceNotFoundException("Technicien non trouvé avec l'id : " + technicienId));

        Assignment assignment = new Assignment();
        assignment.setIncident(incident);
        assignment.setTechnicien(technicien);
        assignment.setDateAffectation(LocalDateTime.now());

        return assignmentRepository.save(assignment);
    }

    public void deleteAssignment(Long id) {
        assignmentRepository.deleteById(id);
    }
    public Assignment updateAssignment(Long id, Assignment updatedAssignment) {
        Assignment existingAssignment = assignmentRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Assignment non trouvé avec l'id : " + id));

        if (updatedAssignment.getIncident() != null) {
            existingAssignment.setIncident(updatedAssignment.getIncident());
        }
        if (updatedAssignment.getTechnicien() != null) {
            existingAssignment.setTechnicien(updatedAssignment.getTechnicien());
        }
        existingAssignment.setDateAffectation(updatedAssignment.getDateAffectation());

        return assignmentRepository.save(existingAssignment);
    }

}
