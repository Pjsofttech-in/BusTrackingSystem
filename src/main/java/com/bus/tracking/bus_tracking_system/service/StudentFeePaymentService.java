package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.StudentFeePaymentRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.StudentFeePaymentResponseDTO;
import com.bus.tracking.bus_tracking_system.mapper.StudentFeePaymentMapper;
import com.bus.tracking.bus_tracking_system.model.Student;
import com.bus.tracking.bus_tracking_system.model.StudentFeePayment;
import com.bus.tracking.bus_tracking_system.repository.StudentFeePaymentRepository;
import com.bus.tracking.bus_tracking_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StudentFeePaymentService {

    private final StudentFeePaymentRepository repository;
    private final StudentRepository studentRepository;

    public StudentFeePaymentService(StudentFeePaymentRepository repository,
                                    StudentRepository studentRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
    }

    // SIMPLE PAY
    public StudentFeePaymentResponseDTO payFees(
            StudentFeePaymentRequestDTO dto) {

        Student student = studentRepository
                .findById(dto.getStudentId())
                .orElseThrow();

        StudentFeePayment payment =
                StudentFeePaymentMapper.toEntity(dto, student);

        payment.setStatus("PAID");
        payment.setPaymentDateTime(LocalDateTime.now());

        StudentFeePayment saved = repository.save(payment);

        return StudentFeePaymentMapper.toDTO(saved);
    }

    // FULL PROCESS
    public StudentFeePaymentResponseDTO processFees(
            StudentFeePaymentRequestDTO dto) {

        Student student = studentRepository
                .findById(dto.getStudentId())
                .orElseThrow();

        StudentFeePayment payment =
                StudentFeePaymentMapper.toEntity(dto, student);

        if ("PAID".equalsIgnoreCase(dto.getStatus())) {
            payment.setPaymentDateTime(LocalDateTime.now());
            payment.setDueDate(null);
        }

        if ("PENDING".equalsIgnoreCase(dto.getStatus())) {
            payment.setTransactionId(null);
        }

        StudentFeePayment saved = repository.save(payment);

        return StudentFeePaymentMapper.toDTO(saved);
    }
}