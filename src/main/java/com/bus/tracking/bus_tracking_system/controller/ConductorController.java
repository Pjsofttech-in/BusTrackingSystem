package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.ConductorRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.ConductorResponseDTO;
import com.bus.tracking.bus_tracking_system.service.ConductorService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://pjsofttech.com")
@RequestMapping("/conductor")
public class ConductorController {

    private final ConductorService service;

    public ConductorController(ConductorService service) {
        this.service = service;
    }

    // ADD
    @PostMapping("/add")
    public ConductorResponseDTO addConductor(
            @RequestBody ConductorRequestDTO dto) {

        return service.addConductor(dto);
    }

    // GET ALL
    @GetMapping("/all")
    public List<ConductorResponseDTO> getAllConductors() {
        return service.getAllConductors();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ConductorResponseDTO getConductor(
            @PathVariable Long id) {

        return service.getConductorById(id);
    }

    // UPDATE
    @PutMapping("/update/{id}")
    public ConductorResponseDTO updateConductor(
            @PathVariable Long id,
            @RequestBody ConductorRequestDTO dto) {

        return service.updateConductor(id, dto);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteConductor(@PathVariable Long id) {
        service.deleteConductor(id);
        return "Conductor deleted";
    }
}