package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.BusTripRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusTripResponseDTO;
import com.bus.tracking.bus_tracking_system.service.BusTripService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trips")
//@CrossOrigin(origins = "https://pjsofttech.com")
@CrossOrigin(origins = "http://localhost:5173")
public class BusTripController {

    private final BusTripService tripService;

    public BusTripController(BusTripService tripService) {
        this.tripService = tripService;
    }

    //  CREATE TRIP
    @PostMapping("/create")
    public ResponseEntity<BusTripResponseDTO> createTrip(
            @RequestBody BusTripRequestDTO dto) {

        return ResponseEntity.ok(tripService.createTrip(dto));
    }

    // GET ALL TRIPS
    @GetMapping("/all")
    public ResponseEntity<List<BusTripResponseDTO>> getAllTrips() {

        return ResponseEntity.ok(tripService.getAllTrips());
    }

    //  GET TRIP BY ID
    @GetMapping("/{id}")
    public ResponseEntity<BusTripResponseDTO> getTripById(
            @PathVariable Long id) {

        return ResponseEntity.ok(tripService.getTripById(id));
    }

    //  UPDATE TRIP
    @PutMapping("/update/{id}")
    public ResponseEntity<BusTripResponseDTO> updateTrip(
            @PathVariable Long id,
            @RequestBody BusTripRequestDTO dto) {

        return ResponseEntity.ok(tripService.updateTrip(id, dto));
    }

    //  DELETE TRIP
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteTrip(@PathVariable Long id) {

        tripService.deleteTrip(id);
        return ResponseEntity.ok("Trip deleted successfully");
    }
}