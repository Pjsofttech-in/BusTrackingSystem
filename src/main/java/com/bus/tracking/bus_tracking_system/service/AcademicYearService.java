package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.AcademicYear;
import com.bus.tracking.bus_tracking_system.repository.AcademicYearRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AcademicYearService {

    private final AcademicYearRepository repo;

    public AcademicYearService(AcademicYearRepository repo) {
        this.repo = repo;
    }

    // Save Academic Year
    public AcademicYear saveYear(AcademicYear year) {
        return repo.save(year);
    }

    //Update Academic Year
    public AcademicYear updateYear(Long id, AcademicYear updatedYear) {
        AcademicYear year = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Academic Year not found"));

        year.setYearName(updatedYear.getYearName());

        return repo.save(year);
    }

    // Get All Academic Years
    public List<AcademicYear> getAllYears() {
        return repo.findAll();
    }

    // Get Academic Year By ID
    public AcademicYear getYearById(Long id) {
        Optional<AcademicYear> year = repo.findById(id);
        return year.orElse(null);
    }

    // Delete Academic Year
    public void deleteYear(Long id) {
        repo.deleteById(id);
    }
}