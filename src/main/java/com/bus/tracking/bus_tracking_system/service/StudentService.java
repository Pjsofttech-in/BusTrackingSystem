package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.Student;
import com.bus.tracking.bus_tracking_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final QrCodeService qrCodeService;

    public StudentService(StudentRepository studentRepository, QrCodeService qrCodeService) {
        this.studentRepository = studentRepository;
        this.qrCodeService = qrCodeService;
    }

    public Student addStudent(Student student) throws Exception {
        // Save student to get ID
        Student saved = studentRepository.save(student);

        // Generate QR code with full URL to scan endpoint
        String qrUrl = qrCodeService.generateAndUploadQR(saved.getId());
        saved.setQrImageUrl(qrUrl);

        // Save again with QR URL
        return studentRepository.save(saved);
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
