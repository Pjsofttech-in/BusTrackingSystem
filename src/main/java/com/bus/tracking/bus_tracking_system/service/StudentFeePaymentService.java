package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.Student;
import com.bus.tracking.bus_tracking_system.model.StudentFeePayment;
import com.bus.tracking.bus_tracking_system.repository.StudentFeePaymentRepository;
import com.bus.tracking.bus_tracking_system.repository.StudentRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class StudentFeePaymentService {

    private final StudentFeePaymentRepository repository;
    private final StudentRepository studentRepository;

    public StudentFeePaymentService(StudentFeePaymentRepository repository,
                                    StudentRepository studentRepository) {
        this.repository = repository;
        this.studentRepository = studentRepository;
    }

    public StudentFeePayment payFees(Long studentId,
                                     Double amount,
                                     String mode) {

        Student student = studentRepository.findById(studentId).orElseThrow();

        StudentFeePayment payment = new StudentFeePayment();
        payment.setStudent(student);
        payment.setAmount(amount);
        payment.setPaymentMode(mode);
        payment.setStatus("PAID");
        payment.setPaymentDate(LocalDate.now());

        return repository.save(payment);
    }
}
