package com.app.bustracking.controller;

import com.app.bustracking.dto.StudentFeePaymentRequestDTO;
import com.app.bustracking.dto.StudentFeePaymentResponseDTO;
import com.app.bustracking.service.StudentFeePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-fees")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@CrossOrigin(origins = "https://pjsofttech.com/bustracking")
//@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class StudentFeePaymentController {

    private final StudentFeePaymentService service;

    @GetMapping
    public ResponseEntity<List<StudentFeePaymentResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentFeePaymentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentFeePaymentResponseDTO>> getByStudent(@PathVariable Long studentId) {
        return ResponseEntity.ok(service.getByStudentId(studentId));
    }

    @PostMapping("/pay")
    public ResponseEntity<StudentFeePaymentResponseDTO> pay(@RequestBody StudentFeePaymentRequestDTO request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.pay(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}