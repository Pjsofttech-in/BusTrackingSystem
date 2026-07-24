package com.app.bustracking.controller;

import com.app.bustracking.dto.MediumRequestDTO;
import com.app.bustracking.dto.MediumResponseDTO;
import com.app.bustracking.service.MediumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mediums")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@CrossOrigin(origins = "https://pjsofttech.com")
//@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class MediumController {

    private final MediumService service;

    @GetMapping
    public ResponseEntity<List<MediumResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MediumResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<MediumResponseDTO> create(@RequestBody MediumRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MediumResponseDTO> update(@PathVariable Long id, @RequestBody MediumRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}