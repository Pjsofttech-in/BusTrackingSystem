package com.app.bustracking.controller;

import com.app.bustracking.dto.BusStopRequestDTO;
import com.app.bustracking.dto.BusStopResponseDTO;
import com.app.bustracking.service.BusStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus-stops")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@CrossOrigin(origins = "https://pjsofttech.com/bustracking")
//@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class BusStopController {

    private final BusStopService service;

    @GetMapping
    public ResponseEntity<List<BusStopResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusStopResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<BusStopResponseDTO> create(@RequestBody BusStopRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusStopResponseDTO> update(@PathVariable Long id, @RequestBody BusStopRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/reached")
    public ResponseEntity<BusStopResponseDTO> markReached(@PathVariable Long id) {
        return ResponseEntity.ok(service.markReached(id));
    }
}