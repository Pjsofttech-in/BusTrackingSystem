package com.app.bustracking.mapper;

import com.app.bustracking.dto.StudentFeePaymentRequestDTO;
import com.app.bustracking.dto.StudentFeePaymentResponseDTO;
import com.app.bustracking.model.StudentFeePaymentModel;
import com.app.bustracking.model.StudentModel;

public class StudentFeePaymentMapper {

    public static StudentFeePaymentModel toEntity(StudentFeePaymentRequestDTO dto, StudentModel student) {
        StudentFeePaymentModel entity = new StudentFeePaymentModel();
        entity.setStudent(student);
        entity.setAmount(dto.getAmount());
        entity.setPaymentMode(dto.getPaymentMode());
        entity.setStatus(dto.getStatus());
        entity.setPaymentDate(dto.getPaymentDate());
        entity.setDueDate(dto.getDueDate());
        entity.setTransactionId(dto.getTransactionId());
        return entity;
    }

    public static StudentFeePaymentResponseDTO toDTO(StudentFeePaymentModel entity) {
        StudentFeePaymentResponseDTO dto = new StudentFeePaymentResponseDTO();
        dto.setId(entity.getId());
        dto.setAmount(entity.getAmount());
        dto.setPaymentMode(entity.getPaymentMode());
        dto.setStatus(entity.getStatus());
        dto.setPaymentDate(entity.getPaymentDate());
        dto.setPaymentDateTime(entity.getPaymentDateTime());
        dto.setDueDate(entity.getDueDate());
        dto.setTransactionId(entity.getTransactionId());
        if (entity.getStudent() != null) {
            dto.setStudent(StudentMapper.toDTO(entity.getStudent()));
        }
        return dto;
    }
}