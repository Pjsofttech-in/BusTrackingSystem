package com.app.bustracking.controller;

import com.app.bustracking.dto.BusRouteStopRequestDTO;
import com.app.bustracking.dto.BusRouteStopResponseDTO;
import com.app.bustracking.service.BusRouteStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus-route-stops")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
//@CrossOrigin(origins = "https://pjsofttech.com/bustracking")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class BusRouteStopController {

    private final BusRouteStopService service;

    @GetMapping
    public ResponseEntity<List<BusRouteStopResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusRouteStopResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/route/{routeId}")
    public ResponseEntity<List<BusRouteStopResponseDTO>> getByRoute(@PathVariable Long routeId) {
        return ResponseEntity.ok(service.getByRouteId(routeId));
    }

    @PostMapping
    public ResponseEntity<BusRouteStopResponseDTO> create(@RequestBody BusRouteStopRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusRouteStopResponseDTO> update(@PathVariable Long id, @RequestBody BusRouteStopRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}