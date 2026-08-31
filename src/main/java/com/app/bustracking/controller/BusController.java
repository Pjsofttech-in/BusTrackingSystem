package com.app.bustracking.controller;

import com.app.bustracking.dto.BusRequestDTO;
import com.app.bustracking.dto.BusResponseDTO;
import com.app.bustracking.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bus")
@CrossOrigin(origins = "https://pjsofttech.com/bustracking", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class BusController {

    private final BusService busService;

    @GetMapping
    public ResponseEntity<List<BusResponseDTO>> getAll() {
        return ResponseEntity.ok(busService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(busService.getById(id));
    }


    @PostMapping
    public ResponseEntity<BusResponseDTO> create(@RequestBody BusRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(busService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BusResponseDTO> update(@PathVariable Long id, @RequestBody BusRequestDTO request) {
        return ResponseEntity.ok(busService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        busService.delete(id);
        return ResponseEntity.noContent().build();
    }
}