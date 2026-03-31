package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.BusTripRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusTripResponseDTO;
import com.bus.tracking.bus_tracking_system.service.BusTripService;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "https://pjsofttech.com")
@RestController
@RequestMapping("/addBus/trips")
public class BusTripController {

    private final BusTripService service;

    public BusTripController(BusTripService service) {
        this.service = service;
    }

    @PostMapping
    public BusTripResponseDTO createTrip(@RequestBody BusTripRequestDTO dto) {
        return service.createTrip(dto);
    }
}