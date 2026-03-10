package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.*;
import com.bus.tracking.bus_tracking_system.model.Student;
import com.bus.tracking.bus_tracking_system.service.EmailService;
import com.bus.tracking.bus_tracking_system.service.StudentService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
//@RequestMapping("/student")
@CrossOrigin(origins = "https://pjsofttech.com")
public class StudentController {

    private final StudentService studentService;
    private final EmailService emailService;

    public StudentController(StudentService studentService,
                             EmailService emailService) {
        this.studentService = studentService;
        this.emailService = emailService;
    }

    // ADD STUDENT (API)
    @PostMapping("/addStudent")
    @ResponseBody
    public StudentResponseDTO addStudent(
            @RequestBody StudentRequestDTO dto) throws Exception {

        return studentService.addStudent(dto);
    }

    // SCAN QR (HTML)
    @GetMapping("/StudentScan/{id}")
    public String scanStudentQr(@PathVariable Long id, Model model) {

        Student student = studentService.getStudentEntity(id);

        if (student != null && student.getParentEmail() != null) {

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

            model.addAttribute("student", student);
        }

        return "student-details";
    }

    // UPDATE
    @PutMapping("/updateStudent/{id}")
    @ResponseBody
    public StudentResponseDTO updateStudent(
            @PathVariable Long id,
            @RequestBody StudentRequestDTO dto) throws Exception {

        return studentService.updateStudent(id, dto);
    }

    // DELETE
    @DeleteMapping("/deleteStudent/{id}")
    @ResponseBody
    public String deleteStudent(@PathVariable Long id) {

        Student existing = studentService.getStudentEntity(id);

        if (existing == null) {
            return "Student not found";
        }

        studentService.deleteStudent(id);
        return "Deleted successfully";
    }

    // GET ALL
    @GetMapping("/all")
    @ResponseBody
    public ResponseEntity<List<StudentResponseDTO>> getAllStudents() {

        return ResponseEntity.ok(studentService.getAllStudents());
    }

    // ACADEMIC INFO (unchanged)
    @GetMapping("/academic/{id}")
    @ResponseBody
    public StudentAcademicInfoDTO getAcademicInfo(@PathVariable Long id) {
        return studentService.getAcademicInfo(id);
    }

    @GetMapping("/academic/all")
    @ResponseBody
    public List<StudentAcademicInfoDTO> getAllAcademicInfo() {
        return studentService.getAllAcademicInfo();
    }
}