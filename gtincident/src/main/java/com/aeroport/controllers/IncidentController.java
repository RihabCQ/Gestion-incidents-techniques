package com.aeroport.controllers;

import com.aeroport.dtos.IncidentDTO;
import com.aeroport.mappers.IncidentMapper;
import com.aeroport.models.Incident;
import com.aeroport.service.IncidentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/incidents")
public class IncidentController {

    @Autowired
    private IncidentService incidentService;

    @GetMapping
    public List<IncidentDTO> getAllIncidents() {
        return incidentService.getAllIncidents()
                .stream()
                .map(IncidentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidentDTO> getIncidentById(@PathVariable Long id) {
        Incident incident = incidentService.getIncidentById(id);
        return ResponseEntity.ok(IncidentMapper.toDTO(incident));
    }

    @PostMapping
    public ResponseEntity<IncidentDTO> createIncident(@RequestBody IncidentDTO dto) {
        Incident incident = IncidentMapper.toEntity(dto);
        Incident saved = incidentService.saveIncident(incident);
        return ResponseEntity.ok(IncidentMapper.toDTO(saved));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteIncident(@PathVariable Long id) {
        incidentService.deleteIncident(id);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/{id}")
    public ResponseEntity<IncidentDTO> updateIncident(@PathVariable Long id, @RequestBody IncidentDTO dto) {
        Incident incident = IncidentMapper.toEntity(dto);
        Incident updated = incidentService.updateIncident(id, incident);
        return ResponseEntity.ok(IncidentMapper.toDTO(updated));
    }

}
