package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.DivisionEntity;
import com.bus.tracking.bus_tracking_system.repository.DivisionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DivisionService {

    @Autowired
    private DivisionEntityRepository divisionRepository;

    public DivisionEntity saveDivision(DivisionEntity division) {
        return divisionRepository.save(division);
    }

    public List<DivisionEntity> getAllDivisions() {
        return divisionRepository.findAll();
    }

    public DivisionEntity getDivisionById(Long id) {
        Optional<DivisionEntity> division = divisionRepository.findById(id);
        return division.orElse(null);
    }

    public DivisionEntity updateDivision(Long id, DivisionEntity updatedDivision) {

        DivisionEntity division = divisionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Division not found"));

        division.setDivisionName(updatedDivision.getDivisionName());

        return divisionRepository.save(division);
    }

    public void deleteDivision(Long id) {
        divisionRepository.deleteById(id);
    }
}