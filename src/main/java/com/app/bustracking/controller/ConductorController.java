package com.app.bustracking.controller;

import com.app.bustracking.dto.ConductorRequestDTO;
import com.app.bustracking.dto.ConductorResponseDTO;
import com.app.bustracking.service.ConductorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/conductors")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
//@CrossOrigin(origins = "https://pjsofttech.com/bustracking")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ConductorController {

    private final ConductorService service;

    @GetMapping
    public ResponseEntity<List<ConductorResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConductorResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ConductorResponseDTO> create(@RequestBody ConductorRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConductorResponseDTO> update(@PathVariable Long id, @RequestBody ConductorRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}