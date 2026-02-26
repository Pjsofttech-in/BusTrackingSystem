package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.StudentFeePaymentRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.StudentFeePaymentResponseDTO;
import com.bus.tracking.bus_tracking_system.model.StudentFeePayment;
import com.bus.tracking.bus_tracking_system.model.Student;

public class StudentFeePaymentMapper {

    // RequestDTO → Entity
    public static StudentFeePayment toEntity(StudentFeePaymentRequestDTO dto,
                                             Student student) {

        StudentFeePayment payment = new StudentFeePayment();

        payment.setStudent(student);
        payment.setAmount(dto.getAmount());
        payment.setPaymentMode(dto.getPaymentMode());
        payment.setStatus(dto.getStatus());
        payment.setTransactionId(dto.getTransactionId());
        payment.setDueDate(dto.getDueDate());

        return payment;
    }

    // Entity → ResponseDTO
    public static StudentFeePaymentResponseDTO toDTO(StudentFeePayment payment) {

        StudentFeePaymentResponseDTO dto = new StudentFeePaymentResponseDTO();

        dto.setId(payment.getId());
        dto.setStudentId(payment.getStudent().getId());
        dto.setAmount(payment.getAmount());
        dto.setPaymentMode(payment.getPaymentMode());
        dto.setStatus(payment.getStatus());
        dto.setPaymentDateTime(payment.getPaymentDateTime());
        dto.setDueDate(payment.getDueDate());
        dto.setTransactionId(payment.getTransactionId());

        return dto;
    }
}