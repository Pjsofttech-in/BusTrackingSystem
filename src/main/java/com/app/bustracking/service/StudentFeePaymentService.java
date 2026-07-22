package com.app.bustracking.service;

import com.app.bustracking.dto.StudentFeePaymentRequestDTO;
import com.app.bustracking.dto.StudentFeePaymentResponseDTO;
import com.app.bustracking.mapper.StudentFeePaymentMapper;
import com.app.bustracking.model.StudentFeePaymentModel;
import com.app.bustracking.model.StudentModel;
import com.app.bustracking.repository.StudentFeePaymentRepository;
import com.app.bustracking.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentFeePaymentService {

    private final StudentFeePaymentRepository paymentRepository;
    private final StudentRepository studentRepository;

    public StudentFeePaymentService(StudentFeePaymentRepository paymentRepository,
                                    StudentRepository studentRepository) {
        this.paymentRepository = paymentRepository;
        this.studentRepository = studentRepository;
    }

    @Transactional
    public StudentFeePaymentResponseDTO pay(StudentFeePaymentRequestDTO dto) {
        StudentModel student = studentRepository.findById(dto.getStudentId())
                .orElseThrow(() -> new RuntimeException("Student not found"));
        StudentFeePaymentModel entity = StudentFeePaymentMapper.toEntity(dto, student);
        StudentFeePaymentModel saved = paymentRepository.save(entity);
        return StudentFeePaymentMapper.toDTO(saved);
    }

    public List<StudentFeePaymentResponseDTO> getAll() {
        return paymentRepository.findAll().stream()
                .map(StudentFeePaymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    public StudentFeePaymentResponseDTO getById(Long id) {
        return paymentRepository.findById(id)
                .map(StudentFeePaymentMapper::toDTO)
                .orElse(null);
    }

    public List<StudentFeePaymentResponseDTO> getByStudentId(Long studentId) {
        return paymentRepository.findByStudentId(studentId).stream()
                .map(StudentFeePaymentMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        paymentRepository.deleteById(id);
    }
}