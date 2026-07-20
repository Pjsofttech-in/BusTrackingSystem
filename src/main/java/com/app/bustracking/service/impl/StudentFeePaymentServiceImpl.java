package com.app.bustracking.service.impl;

import com.app.bustracking.Mapper.StudentFeePaymentMapper;
import com.app.bustracking.Model.StudentFeePaymentModel;
import com.app.bustracking.Model.StudentModel;
import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.repository.StudentFeePaymentRepository;
import com.app.bustracking.repository.StudentRepository;
import com.app.bustracking.Request.StudentFeePaymentRequest;
import com.app.bustracking.Response.StudentFeePaymentResponse;
import com.app.bustracking.service.StudentFeePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StudentFeePaymentServiceImpl implements StudentFeePaymentService {

    private final StudentFeePaymentRepository paymentRepository;
    private final StudentRepository studentRepository;
    private final StudentFeePaymentMapper mapper;

    @Override
    @Transactional
    public StudentFeePaymentResponse payFee(StudentFeePaymentRequest request) {
        StudentModel student = studentRepository.findById(request.studentId())
                .orElseThrow(() -> new ResourceNotFoundException("Student not found: " + request.studentId()));

        StudentFeePaymentModel payment = mapper.toModel(request);
        payment.setStudent(student);
        payment.setStatus(request.status() != null ? request.status() : "SUCCESS");
        payment.setTransactionId(request.transactionId() != null ? request.transactionId() : "TXN" + System.currentTimeMillis());

        StudentFeePaymentModel saved = paymentRepository.save(payment);
        return mapper.toResponse(saved);
    }

    @Override
    public List<StudentFeePaymentResponse> getHistoryByStudentId(Long studentId) {
        if (!studentRepository.existsById(studentId)) {
            throw new ResourceNotFoundException("Student not found: " + studentId);
        }
        return paymentRepository.findByStudentIdOrderByPaymentDateTimeDesc(studentId)
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<StudentFeePaymentResponse> getAll() {
        return paymentRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}