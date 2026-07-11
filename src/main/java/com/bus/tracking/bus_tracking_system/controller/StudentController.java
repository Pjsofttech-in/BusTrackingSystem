package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.*;
import com.bus.tracking.bus_tracking_system.model.Student;
import com.bus.tracking.bus_tracking_system.service.EmailService;
import com.bus.tracking.bus_tracking_system.service.StudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
//@CrossOrigin(origins = "https://pjsofttech.com")
public class StudentController {

    private final StudentService studentService;
    private final EmailService emailService;

    public StudentController(StudentService studentService,
                             EmailService emailService) {
        this.studentService = studentService;
        this.emailService = emailService;
    }

    //  CREATE STUDENT
    @PostMapping("/createStudent")
    public ResponseEntity<StudentResponseDTO> createStudent(
            @RequestBody StudentRequestDTO dto) throws Exception {

        return ResponseEntity.ok(studentService.addStudent(dto));
    }

    //  GET ALL STUDENTS
    @GetMapping("/getAllStudents")
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {

        return ResponseEntity.ok(studentService.getAllStudents());
    }

    //  GET STUDENT BY ID
    @GetMapping("/getStudent/{id}")
    public ResponseEntity<StudentResponseDTO> getStudentById(
            @PathVariable Long id) {

        return ResponseEntity.ok(studentService.getStudentById(id));
    }

    //  UPDATE STUDENT
    @PutMapping("/updateStudent/{id}")
    public ResponseEntity<StudentResponseDTO> updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestDTO dto) throws Exception {

        return ResponseEntity.ok(studentService.updateStudent(id, dto));
    }

    // DELETE STUDENT
    @DeleteMapping("/deleteStudent/{id}")
    public ResponseEntity<String> deleteStudent(@PathVariable Long id) {

        Student student = studentService.getStudentEntity(id);

        if (student == null) {
            return ResponseEntity.badRequest().body("Student not found");
        }

        studentService.deleteStudent(id);
        return ResponseEntity.ok("Student deleted successfully");
    }

    // GET ACADEMIC INFO BY ID
    @GetMapping("/getAcademic/{id}")
    public ResponseEntity<StudentAcademicInfoDTO> getAcademicInfo(
            @PathVariable Long id) {

        return ResponseEntity.ok(studentService.getAcademicInfo(id));
    }

    //  GET ALL ACADEMIC INFO
    @GetMapping("/getAllAcademic")
    public ResponseEntity<List<StudentAcademicInfoDTO>> getAllAcademicInfo() {

        return ResponseEntity.ok(studentService.getAllAcademicInfo());
    }

    // SCAN STUDENT QR + SEND EMAIL
    @GetMapping("/scanStudent/{id}")
    public ResponseEntity<String> scanStudent(@PathVariable Long id) {

        Student student = studentService.getStudentEntity(id);

        if (student == null) {
            return ResponseEntity.badRequest().body("Student not found");
        }

        if (student.getParentEmail() != null) {

            String subject = "Your Child is on the Bus";

            String body = "Hello " + student.getParentName() + ",\n\n" +
                    "Your child " + student.getName() +
                    " (Roll No: " + student.getRollNumber() + ") " +
                    "has boarded the bus.\n\n" +
                    "Regards,\nBus Tracking System";

            emailService.sendEmail(
                    student.getParentEmail(),
                    subject,
                    body
            );
        }

        return ResponseEntity.ok("QR scanned & email sent");
    }
}