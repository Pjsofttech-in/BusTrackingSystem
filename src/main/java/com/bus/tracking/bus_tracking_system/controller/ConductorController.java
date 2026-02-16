package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.Conductor;
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

    @PostMapping("/add")
    public Conductor addConductor(@RequestBody Conductor conductor) {
        return service.addConductor(conductor);
    }

    @GetMapping("/all")
    public List<Conductor> getAllConductors() {
        return service.getAllConductors();
    }

    @GetMapping("/{id}")
    public Conductor getConductor(@PathVariable Long id) {
        return service.getConductorById(id);
    }

    @PutMapping("/update/{id}")
    public Conductor updateConductor(@PathVariable Long id, @RequestBody Conductor updated) {
        Conductor existing = service.getConductorById(id);
        if (existing == null) return null;

        existing.setName(updated.getName());
        existing.setPhone(updated.getPhone());
        existing.setStatus(updated.getStatus());
        existing.setAssignedBusId(updated.getAssignedBusId());

        existing.setHouseNo(updated.getHouseNo());
        existing.setStreet(updated.getStreet());
        existing.setCity(updated.getCity());
        existing.setState(updated.getState());
        existing.setPincode(updated.getPincode());

        return service.addConductor(existing);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteConductor(@PathVariable Long id) {
        service.deleteConductor(id);
        return "Conductor deleted";
    }
}
