package com.app.bustracking.controller;

import com.app.bustracking.dto.StudentScanRequestDTO;
import com.app.bustracking.dto.StudentScanResponseDTO;
import com.app.bustracking.service.StudentScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-scans")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
//@CrossOrigin(origins = "https://pjsofttech.com")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class StudentScanController {

    private final StudentScanService service;

    @GetMapping
    public ResponseEntity<List<StudentScanResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentScanResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentScanResponseDTO>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getByStudentId(studentId));
    }

    @GetMapping("/bus/{busId}")
    public ResponseEntity<List<StudentScanResponseDTO>> getByBus(@PathVariable Long busId) {
        return ResponseEntity.ok(service.getByBusId(busId));
    }

    @PostMapping
    public ResponseEntity<StudentScanResponseDTO> recordScan(@RequestBody StudentScanRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.recordScan(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}