package com.app.bustracking.controller;

import com.app.bustracking.dto.DivisionRequestDTO;
import com.app.bustracking.dto.DivisionResponseDTO;
import com.app.bustracking.service.DivisionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/divisions")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
//@CrossOrigin(origins = "https://pjsofttech.com/bustracking")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class DivisionController {

    private final DivisionService service;

    @GetMapping
    public ResponseEntity<List<DivisionResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DivisionResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<DivisionResponseDTO> create(@RequestBody DivisionRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DivisionResponseDTO> update(@PathVariable Long id, @RequestBody DivisionRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}