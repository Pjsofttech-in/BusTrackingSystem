package com.app.bustracking.controller;

import com.app.bustracking.dto.BusRouteRequestDTO;
import com.app.bustracking.dto.BusRouteResponseDTO;
import com.app.bustracking.service.BusRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus-routes")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@CrossOrigin(origins = "https://pjsofttech.com")
//@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class BusRouteController {

    private final BusRouteService service;

    @GetMapping
    public ResponseEntity<List<BusRouteResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusRouteResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<BusRouteResponseDTO> create(@RequestBody BusRouteRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusRouteResponseDTO> update(@PathVariable Long id, @RequestBody BusRouteRequestDTO request) {
        return ResponseEntity.ok(service.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}