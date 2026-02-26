package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.StudentScanResponseDTO;
import com.bus.tracking.bus_tracking_system.mapper.StudentScanMapper;
import com.bus.tracking.bus_tracking_system.model.Student;
import com.bus.tracking.bus_tracking_system.model.StudentScan;
import com.bus.tracking.bus_tracking_system.model.Bus;
import com.bus.tracking.bus_tracking_system.repository.BusRepository;
import com.bus.tracking.bus_tracking_system.repository.StudentRepository;
import com.bus.tracking.bus_tracking_system.repository.StudentScanRepository;

import org.springframework.stereotype.Service;

@Service
public class StudentScanService {

    private final StudentScanRepository repo;
    private final BusRepository busRepository;
    private final StudentRepository studentRepository;
    private final EmailService emailService;

    public StudentScanService(StudentScanRepository repo,
                              BusRepository busRepository,
                              StudentRepository studentRepository,
                              EmailService emailService) {
        this.repo = repo;
        this.busRepository = busRepository;
        this.studentRepository = studentRepository;
        this.emailService = emailService;
    }

    public StudentScanResponseDTO scanStudent(Long studentId, Long busId) {

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        Bus bus = busRepository.findById(busId)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        if (student.isInBus()) {
            throw new RuntimeException("Student already inside bus");
        }

        // Update student status
        student.setInBus(true);
        studentRepository.save(student);

        // Create scan record
        StudentScan scan = new StudentScan();
        scan.setStudent(student);
        scan.setBus(bus);

        StudentScan savedScan = repo.save(scan);

        // Send email
        if (student.getParentEmail() != null &&
                !student.getParentEmail().isEmpty()) {

            String subject = "Bus Update: Your Child is on the Bus";

            String text = "Dear " + student.getParentName() + ",\n\n" +
                    "Your child " + student.getName() +
                    " (Roll No: " + student.getRollNumber() +
                    ") has boarded the bus.\n\nRegards,\nBus Tracking System";

            emailService.sendEmail(
                    student.getParentEmail(),
                    subject,
                    text
            );
        }

        return StudentScanMapper.toDTO(savedScan);
    }

    public long countStudentsInBus(Long busId) {
        return repo.countByBusId(busId);
    }
}