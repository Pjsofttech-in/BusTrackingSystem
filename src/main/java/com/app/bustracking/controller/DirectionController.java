package com.app.bustracking.controller;

import com.app.bustracking.dto.DirectionRequestDTO;
import com.app.bustracking.dto.DirectionResponseDTO;
import com.app.bustracking.service.DirectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/directions")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@CrossOrigin(origins = "https://pjsofttech.com/bustracking")
//@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class DirectionController {

    private final DirectionService service;

    @GetMapping
    public ResponseEntity<List<DirectionResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DirectionResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<DirectionResponseDTO> create(@RequestBody DirectionRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DirectionResponseDTO> update(@PathVariable Long id, @RequestBody DirectionRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}