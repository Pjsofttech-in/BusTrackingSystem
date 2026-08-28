package com.app.bustracking.controller;

import com.app.bustracking.dto.FeeStructureRequestDTO;
import com.app.bustracking.dto.FeeStructureResponseDTO;
import com.app.bustracking.service.FeeStructureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fee-structures")
//@CrossOrigin(origins = "https://pjsofttech.com/bustracking")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class FeeStructureController {

    private final FeeStructureService service;

    @GetMapping
    public ResponseEntity<List<FeeStructureResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FeeStructureResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/by-year/{yearName}")
    public ResponseEntity<List<FeeStructureResponseDTO>> getByAcademicYear(@PathVariable String yearName) {
        return ResponseEntity.ok(service.getByAcademicYear(yearName));
    }

    @GetMapping("/by-route/{routeId}/year/{yearName}")
    public ResponseEntity<FeeStructureResponseDTO> getByRouteAndYear(@PathVariable Long routeId, @PathVariable String yearName) {
        return ResponseEntity.ok(service.getByRouteAndYear(routeId, yearName));
    }

    @PostMapping
    public ResponseEntity<FeeStructureResponseDTO> create(@Valid @RequestBody FeeStructureRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FeeStructureResponseDTO> update(@PathVariable Long id, @Valid @RequestBody FeeStructureRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}