package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.Conductor;
import com.bus.tracking.bus_tracking_system.repository.ConductorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConductorService {

    private final ConductorRepository repo;

    public ConductorService(ConductorRepository repo) {
        this.repo = repo;
    }

    public Conductor addConductor(Conductor conductor) {
        return repo.save(conductor);
    }

    public List<Conductor> getAllConductors() {
        return repo.findAll();
    }

    public Conductor getConductorById(Long id) {
        return repo.findById(id).orElse(null);
    }
    // ConductorService.java
    public void deleteConductor(Long id) {
        repo.deleteById(id);
    }
}
