package com.app.bustracking.controller;

import com.app.bustracking.dto.DriverRequestDTO;
import com.app.bustracking.dto.DriverResponseDTO;
import com.app.bustracking.service.DriverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/drivers")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@CrossOrigin(origins = "https://pjsofttech.com/bustracking")
//@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class DriverController {

    private final DriverService service;

    @GetMapping
    public ResponseEntity<List<DriverResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DriverResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<DriverResponseDTO> create(@RequestBody DriverRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DriverResponseDTO> update(@PathVariable Long id, @RequestBody DriverRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}