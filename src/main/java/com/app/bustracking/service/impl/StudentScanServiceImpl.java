package com.app.bustracking.service.impl;

import com.app.bustracking.Mapper.StudentScanMapper;
import com.app.bustracking.Model.BusModel;
import com.app.bustracking.Model.StudentModel;
import com.app.bustracking.Model.StudentScanModel;
import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.repository.BusRepository;
import com.app.bustracking.repository.StudentRepository;
import com.app.bustracking.repository.StudentScanRepository;
import com.app.bustracking.Request.StudentScanRequest;
import com.app.bustracking.Response.StudentScanResponse;
import com.app.bustracking.service.StudentScanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentScanServiceImpl implements StudentScanService {

    private final StudentScanRepository scanRepository;
    private final StudentRepository studentRepository;
    private final BusRepository busRepository;
    private final StudentScanMapper mapper;

    @Override
    @Transactional
    public StudentScanResponse recordScan(StudentScanRequest request) {
        StudentModel student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + request.studentId()));
        BusModel bus = busRepository.findById(request.busId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found: " + request.busId()));

        StudentScanModel scan = mapper.toModel(request);
        scan.setStudent(student);
        scan.setBus(bus);
        StudentScanModel saved = scanRepository.save(scan);
        return mapper.toResponse(saved);
    }

    @Override
    public List<StudentScanResponse> getByStudentId(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found: " + studentId);
        }
        return scanRepository.findByStudentIdOrderByScannedAtDesc(studentId)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentScanResponse> getByBusId(Long busId) {
        if (!busRepository.existsById(busId)) {
            throw new ResourceNotFoundException("Bus not found: " + busId);
        }
        return scanRepository.findByBusIdOrderByScannedAtDesc(busId)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentScanResponse> getAll() {
        return scanRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}