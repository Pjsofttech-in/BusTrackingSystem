package com.app.bustracking.service;

import com.app.bustracking.dto.StudentScanRequestDTO;
import com.app.bustracking.dto.StudentScanResponseDTO;
import com.app.bustracking.mapper.StudentScanMapper;
import com.app.bustracking.model.BusModel;
import com.app.bustracking.model.StudentModel;
import com.app.bustracking.model.StudentScanModel;
import com.app.bustracking.repository.BusRepository;
import com.app.bustracking.repository.StudentRepository;
import com.app.bustracking.repository.StudentScanRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentScanService {

    private final StudentScanRepository scanRepository;
    private final StudentRepository studentRepository;
    private final BusRepository busRepository;

    public StudentScanService(StudentScanRepository scanRepository,
                              StudentRepository studentRepository,
                              BusRepository busRepository) {
        this.scanRepository = scanRepository;
        this.studentRepository = studentRepository;
        this.busRepository = busRepository;
    }

    @Transactional
    public StudentScanResponseDTO recordScan(StudentScanRequestDTO dto) {
        StudentModel student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        BusModel bus = busRepository.findById(dto.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        StudentScanModel entity = StudentScanMapper.toEntity(dto, student, bus);
        StudentScanModel saved = scanRepository.save(entity);
        return StudentScanMapper.toDTO(saved);
    }

    public List<StudentScanResponseDTO> getAll() {
        return scanRepository.findAll().stream()
                .map(StudentScanMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StudentScanResponseDTO getById(Long id) {
        return scanRepository.findById(id)
                .map(StudentScanMapper::toDTO)
                .orElse(null);
    }

    // ✅ Fixed: repository methods now return List<StudentScanModel>
    public List<StudentScanResponseDTO> getByStudentId(Long studentId) {
        return scanRepository.findByStudentId(studentId).stream()
                .map(StudentScanMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<StudentScanResponseDTO> getByBusId(Long busId) {
        return scanRepository.findByBusId(busId).stream()
                .map(StudentScanMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        scanRepository.deleteById(id);
    }
}