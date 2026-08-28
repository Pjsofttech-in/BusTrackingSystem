package com.app.bustracking.controller;

import com.app.bustracking.dto.BusLocationRequestDTO;
import com.app.bustracking.dto.BusLocationResponseDTO;
import com.app.bustracking.service.BusLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus-locations")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
//@CrossOrigin(origins = "https://pjsofttech.com/bustracking")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class BusLocationController {

    private final BusLocationService service;

    @GetMapping
    public ResponseEntity<List<BusLocationResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusLocationResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/bus/{busId}/latest")
    public ResponseEntity<BusLocationResponseDTO> getLatestByBus(@PathVariable Long busId) {
        BusLocationResponseDTO location = service.getLatestByBusId(busId);
        if (location == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(location);
    }

    @GetMapping("/bus/{busId}/history")
    public ResponseEntity<List<BusLocationResponseDTO>> getHistory(
            @PathVariable Long busId,
            @RequestParam(defaultValue = "20") int limit) {
        return ResponseEntity.ok(service.getHistoryByBusId(busId, limit));
    }

    @PostMapping
    public ResponseEntity<BusLocationResponseDTO> create(@RequestBody BusLocationRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.saveLocation(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}