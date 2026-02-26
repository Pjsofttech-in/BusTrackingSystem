package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.*;
import com.bus.tracking.bus_tracking_system.mapper.StudentMapper;
import com.bus.tracking.bus_tracking_system.model.Student;
import com.bus.tracking.bus_tracking_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository studentRepository;
    private final QrCodeService qrCodeService;

    public StudentService(StudentRepository studentRepository,
                          QrCodeService qrCodeService) {
        this.studentRepository = studentRepository;
        this.qrCodeService = qrCodeService;
    }

    // CREATE
    public StudentResponseDTO addStudent(StudentRequestDTO dto) throws Exception {

        Student student = StudentMapper.toEntity(dto);

        // Save first to generate ID
        Student saved = studentRepository.save(student);

        // Generate QR
        String qrUrl = qrCodeService.generateAndUploadQR(saved.getId());
        saved.setQrImageUrl(qrUrl);

        Student updated = studentRepository.save(saved);

        return StudentMapper.toDTO(updated);
    }

    // GET BY ID (Entity version for internal use like email)
    public Student getStudentEntity(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // GET BY ID (DTO version for API)
    public StudentResponseDTO getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(StudentMapper::toDTO)
                .orElse(null);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StudentAcademicInfoDTO getAcademicInfo(Long id) {
        return studentRepository.getAcademicInfo(id);
    }

    public List<StudentAcademicInfoDTO> getAllAcademicInfo() {
        return studentRepository.getAllAcademicInfo();
    }

    public StudentResponseDTO updateStudent(Long id,
                                            StudentRequestDTO dto) throws Exception {

        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        existing.setName(dto.getName());
        existing.setRollNumber(dto.getRollNumber());
        existing.setAge(dto.getAge());
        existing.setBloodGroup(dto.getBloodGroup());
        existing.setParentName(dto.getParentName());
        existing.setParentPhone(dto.getParentPhone());
        existing.setParentEmail(dto.getParentEmail());

        // ⚠ Do NOT regenerate QR here
        Student updated = studentRepository.save(existing);

        return StudentMapper.toDTO(updated);
    }
}