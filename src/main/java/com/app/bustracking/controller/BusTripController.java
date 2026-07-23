package com.app.bustracking.controller;

import com.app.bustracking.dto.BusTripRequestDTO;
import com.app.bustracking.dto.BusTripResponseDTO;
import com.app.bustracking.service.BusTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus-trips")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
//@CrossOrigin(origins = "https://pjsofttech.com")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class BusTripController {

    private final BusTripService service;

    @GetMapping
    public ResponseEntity<List<BusTripResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusTripResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<BusTripResponseDTO> create(@RequestBody BusTripRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusTripResponseDTO> update(@PathVariable Long id, @RequestBody BusTripRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}