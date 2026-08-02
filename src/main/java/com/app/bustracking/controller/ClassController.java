package com.app.bustracking.controller;

import com.app.bustracking.dto.ClassRequestDTO;
import com.app.bustracking.dto.ClassResponseDTO;
import com.app.bustracking.service.ClassService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classes")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@CrossOrigin(origins = "https://pjsofttech.com/bustracking")
//@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class ClassController {

    private final ClassService service;

    @GetMapping
    public ResponseEntity<List<ClassResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ClassResponseDTO> create(@RequestBody ClassRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassResponseDTO> update(@PathVariable Long id, @RequestBody ClassRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}