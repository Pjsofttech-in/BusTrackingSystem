package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.Student;
import com.bus.tracking.bus_tracking_system.service.EmailService;
import com.bus.tracking.bus_tracking_system.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/student")
@CrossOrigin(origins = "https://pjsofttech.com")
public class StudentController {

    private final StudentService studentService;
    private final EmailService emailService;

    public StudentController(StudentService studentService, EmailService emailService) {
        this.studentService = studentService;
        this.emailService = emailService;
    }

    @PostMapping("/add")
    @ResponseBody
    public Student addStudent(@RequestBody Student student) throws Exception {
        return studentService.addStudent(student);
    }

    @GetMapping("/scan/{id}")
    public String scanStudentQr(@PathVariable Long id, Model model) {
        Student student = studentService.getStudentById(id);

        if(student != null && student.getParentEmail() != null) {
            // send email to parent
            String subject = "Your Child is on the Bus";
            String body = "Hello " + student.getParentName() + ",\n\n" +
                    "Your child " + student.getName() + " (Roll No: " + student.getRollNumber() + ") " +
                    "has boarded the bus.\n\n" +
                    "Regards,\nBus Tracking System";

            emailService.sendEmail(student.getParentEmail(), subject, body);

            model.addAttribute("student", student);
        }

        return "student-details"; // HTML template name
    }

    // Update student
    @PutMapping("/update/{id}")
    public Student updateStudent(@PathVariable Long id, @RequestBody Student updated) throws Exception {
        Student existing = studentService.getStudentById(id);
        if (existing == null) return null;

        existing.setName(updated.getName());
        existing.setRollNumber(updated.getRollNumber());
        existing.setAge(updated.getAge());
        existing.setBloodGroup(updated.getBloodGroup());
        existing.setParentName(updated.getParentName());
        existing.setParentPhone(updated.getParentPhone());
        existing.setParentEmail(updated.getParentEmail());

        return studentService.addStudent(existing); // saves and updates QR
    }

    // Delete student
    @DeleteMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        Student student = studentService.getStudentById(id);
        if (student != null) {
            studentService.deleteStudent(id); // add delete method in service
            return "Deleted successfully";
        }
        return "Student not found";
    }
}
