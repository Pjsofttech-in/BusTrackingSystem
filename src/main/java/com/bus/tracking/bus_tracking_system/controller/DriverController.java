package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.*;
import com.bus.tracking.bus_tracking_system.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
@CrossOrigin(origins = "https://pjsofttech.com")
public class DriverController {

    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    // ADD
    @PostMapping("/add")
    public DriverResponseDTO addDriver(@RequestBody DriverRequestDTO dto) {
        return service.addDriver(dto);
    }

    // GET ALL
    @GetMapping("/all")
    public List<DriverResponseDTO> getAllDrivers() {
        return service.getAllDrivers();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public DriverResponseDTO getDriver(@PathVariable Long id) {
        return service.getDriverById(id);
    }

    // LOGIN
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody DriverLoginRequestDTO dto) {

        DriverLoginResponseDTO response = service.login(dto);

        if (response == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        return ResponseEntity.ok(response);
    }

    // DELETE
    @DeleteMapping("/delete/{id}")
    public String deleteDriver(@PathVariable Long id) {
        service.deleteDriver(id);
        return "Driver deleted";
    }
}