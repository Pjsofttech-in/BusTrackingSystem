package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.Student;
import com.bus.tracking.bus_tracking_system.repository.StudentRepository;
import com.bus.tracking.bus_tracking_system.service.EmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/scan")
public class StudentScanController {

    private final StudentRepository studentRepo;
    private final EmailService emailService;

    public StudentScanController(StudentRepository studentRepo, EmailService emailService) {
        this.studentRepo = studentRepo;
        this.emailService = emailService;
    }

    // Scan QR and show student details
    @PostMapping("/{studentId}")
    public ResponseEntity<?> scanStudent(@PathVariable Long studentId) {

        Student student = studentRepo.findById(studentId).orElse(null);

        if (student == null) {
            return ResponseEntity.badRequest().body("Invalid QR - Student not found");
        }

        if (student.isInBus()) {
            return ResponseEntity.ok("Student already inside bus");
        }
        student.setInBus(true);
        studentRepo.save(student);

        // Send email to parent
        String parentEmail = student.getParentEmail();
        if (parentEmail != null && !parentEmail.isEmpty()) {
            String subject = "Bus Update: Your Child is on the Bus";
            String text = "Dear " + student.getParentName() + ",\n\n" +
                    "Your child " + student.getName() + " (Roll No: " + student.getRollNumber() + ") has boarded the bus.\n\n" +
                    "Regards,\nBus Tracking System";
            emailService.sendEmail(parentEmail, subject, text);
        }

        // Return basic details
        Map<String, Object> response = new HashMap<>();
        response.put("id", student.getId());
        response.put("name", student.getName());
        response.put("rollNumber", student.getRollNumber());
        response.put("parentName", student.getParentName());
        response.put("parentPhone", student.getParentPhone());
        response.put("bloodGroup", student.getBloodGroup());

        return ResponseEntity.ok(response);
    }

    // Count students in bus
    @GetMapping("/count")
    public long countStudentsInBus() {
        return studentRepo.findAll()
                .stream()
                .filter(Student::isInBus)
                .count();
    }
}
