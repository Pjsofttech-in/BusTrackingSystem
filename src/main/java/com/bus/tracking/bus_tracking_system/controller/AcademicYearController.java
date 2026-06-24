package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.AcademicYear;
import com.bus.tracking.bus_tracking_system.service.AcademicYearService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://pjsofttech.com")
public class AcademicYearController {

    private final AcademicYearService service;

    public AcademicYearController(AcademicYearService service) {
        this.service = service;
    }

    // ADD
    @PostMapping("/addAcademicYear")
    public AcademicYear addYear(@RequestBody AcademicYear year) {
        return service.saveYear(year);
    }

    // GET ALL
    @GetMapping("/getALLAcademicYear")
    public List<AcademicYear> getAllYears() {
        return service.getAllYears();
    }

    // UPDATE ALL
    @PutMapping("/updateAcademicYear/{id}")
    public AcademicYear updateYear(@PathVariable Long id,
                                   @RequestBody AcademicYear year) {
        return service.updateYear(id, year);
    }

    //GET BY ID
    @GetMapping("/getAcademicYear/{id}")
    public AcademicYear getYear(@PathVariable Long id) {
        return service.getYearById(id);
    }

    // DELETE
    @DeleteMapping("/deleteAcademicYear/{id}")
    public String deleteYear(@PathVariable Long id) {
        service.deleteYear(id);
        return "Academic Year deleted";
    }
}