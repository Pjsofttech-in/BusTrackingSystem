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

        Student saved = studentRepository.save(student);

        String qrUrl = qrCodeService.generateAndUploadQR(saved.getId());
        saved.setQrImageUrl(qrUrl);

        return StudentMapper.toDTO(studentRepository.save(saved));
    }

    // GET ENTITY
    public Student getStudentEntity(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    // GET DTO
    public StudentResponseDTO getStudentById(Long id) {
        return studentRepository.findById(id)
                .map(StudentMapper::toDTO)
                .orElse(null);
    }

    // DELETE
    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }

    // GET ALL
    public List<StudentResponseDTO> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(StudentMapper::toDTO)
                .collect(Collectors.toList());
    }

    // ACADEMIC
    public StudentAcademicInfoDTO getAcademicInfo(Long id) {
        return studentRepository.getAcademicInfo(id);
    }

    public List<StudentAcademicInfoDTO> getAllAcademicInfo() {
        return studentRepository.getAllAcademicInfo();
    }

    // UPDATE (CLEAN FIX)
    public StudentResponseDTO updateStudent(Long id,
                                            StudentRequestDTO dto) throws Exception {

        Student existing = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        StudentMapper.updateEntity(existing, dto);

        Student updated = studentRepository.save(existing);

        return StudentMapper.toDTO(updated);
    }
}