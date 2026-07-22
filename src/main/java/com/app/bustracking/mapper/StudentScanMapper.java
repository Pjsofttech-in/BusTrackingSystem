package com.app.bustracking.mapper;

import com.app.bustracking.dto.StudentScanRequestDTO;
import com.app.bustracking.dto.StudentScanResponseDTO;
import com.app.bustracking.model.StudentScanModel;
import com.app.bustracking.model.StudentModel;
import com.app.bustracking.model.BusModel;

public class StudentScanMapper {

    public static StudentScanModel toEntity(StudentScanRequestDTO dto, StudentModel student, BusModel bus) {
        StudentScanModel entity = new StudentScanModel();
        entity.setStudent(student);
        entity.setBus(bus);
        return entity;
    }

    public static StudentScanResponseDTO toDTO(StudentScanModel entity) {
        StudentScanResponseDTO dto = new StudentScanResponseDTO();
        dto.setId(entity.getId());
        dto.setScannedAt(entity.getScannedAt());
        if (entity.getStudent() != null) {
            dto.setStudent(StudentMapper.toDTO(entity.getStudent()));
        }
        if (entity.getBus() != null) {
            dto.setBus(BusMapper.toDTO(entity.getBus()));
        }
        return dto;
    }
}