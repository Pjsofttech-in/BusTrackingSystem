package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.BusLocationRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusLocationResponseDTO;
import com.bus.tracking.bus_tracking_system.service.BusLocationService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
@CrossOrigin(origins = "https://pjsofttech.com")
public class LocationController {

    private final BusLocationService service;

    public LocationController(BusLocationService service) {
        this.service = service;
    }

    @PostMapping("/update")
    public BusLocationResponseDTO updateLocation(
            @RequestBody BusLocationRequestDTO dto) {

        return service.updateLocation(dto);
    }

    @GetMapping("/{id}")
    public BusLocationResponseDTO getLocationById(
            @PathVariable Long id) {

        return service.getLocationById(id);
    }

    @GetMapping("/latest/{busId}")
    public BusLocationResponseDTO getLatestLocation(
            @PathVariable Long busId) {

        return service.getLatestLocation(busId);
    }

    @GetMapping("/history/{busId}")
    public List<BusLocationResponseDTO> getLocationHistory(
            @PathVariable Long busId) {

        return service.getLocationHistory(busId);
    }
    @DeleteMapping("/{id}")
    public String deleteLocation(
            @PathVariable Long id) {

        service.deleteLocation(id);
        return "Location deleted";
    }
}