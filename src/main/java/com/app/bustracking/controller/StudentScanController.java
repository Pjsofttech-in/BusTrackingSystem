package com.app.bustracking.controller;

import com.app.bustracking.Request.StudentScanRequest;
import com.app.bustracking.Response.StudentScanResponse;
import com.app.bustracking.service.StudentScanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-scans")
//@CrossOrigin(origins = "https://pjsofttech.com", originPatterns = "http://localhost:5173")
@RequiredArgsConstructor
public class StudentScanController {

    private final StudentScanService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentScanResponse recordScan(@Valid @RequestBody StudentScanRequest request) {
        return service.recordScan(request);
    }

    @GetMapping("/student/{studentId}")
    public List<StudentScanResponse> getByStudent(@PathVariable Long studentId) {
        return service.getByStudentId(studentId);
    }

    @GetMapping("/bus/{busId}")
    public List<StudentScanResponse> getByBus(@PathVariable Long busId) {
        return service.getByBusId(busId);
    }

    @GetMapping
    public List<StudentScanResponse> getAll() {
        return service.getAll();
    }
}