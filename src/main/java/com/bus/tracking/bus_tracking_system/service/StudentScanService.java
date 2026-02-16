package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.Student;
import com.bus.tracking.bus_tracking_system.model.StudentScan;
import com.bus.tracking.bus_tracking_system.repository.StudentScanRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentScanService {

    private final StudentScanRepository repo;

    public StudentScanService(StudentScanRepository repo) {
        this.repo = repo;
    }

    public StudentScan scanStudent(Student student, Long busId) {
        StudentScan scan = new StudentScan();
        scan.setStudentId(student.getId());
        scan.setBusId(busId);
        return repo.save(scan);
    }

    public long countStudentsInBus(Long busId) {
        return repo.countByBusId(busId);
    }
}
