package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.StudentScanResponseDTO;
import com.bus.tracking.bus_tracking_system.service.StudentScanService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "https://pjsofttech.com")
@RestController
@RequestMapping("/scan")
public class StudentScanController {

    private final StudentScanService service;

    public StudentScanController(StudentScanService service) {
        this.service = service;
    }

    @PostMapping("/scanExit/{studentId}/{busId}")
    public ResponseEntity<String> scanExit(
            @PathVariable Long studentId,
            @PathVariable Long busId) {

        // call service here later

        return ResponseEntity.ok("Student exited bus");
    }

    @PostMapping("/{studentId}/{busId}")
    public ResponseEntity<StudentScanResponseDTO> scanStudent(
            @PathVariable Long studentId,
            @PathVariable Long busId) {

        StudentScanResponseDTO scan =
                service.scanStudent(studentId, busId);

        return ResponseEntity.ok(scan);
    }

    @GetMapping("/countStudents/{busId}")
    public long countStudentsInBus(
            @PathVariable Long busId) {

        return service.countStudentsInBus(busId);
    }
}