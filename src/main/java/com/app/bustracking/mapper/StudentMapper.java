package com.app.bustracking.mapper;

import com.app.bustracking.dto.StudentRequestDTO;
import com.app.bustracking.dto.StudentResponseDTO;
import com.app.bustracking.model.StudentModel;

public class StudentMapper {

    public static StudentModel toEntity(StudentRequestDTO dto) {
        StudentModel entity = new StudentModel();
        entity.setName(dto.getName());
        entity.setRollNumber(dto.getRollNumber());
        entity.setAdmission(dto.getAdmission());
        entity.setPresent(dto.getPresent());
        entity.setStatus(dto.getStatus());
        entity.setInBus(dto.getInBus());
        entity.setQrImageUrl(dto.getQrImageUrl());
        entity.setParentName(dto.getParentName());
        entity.setParentPhone(dto.getParentPhone());
        entity.setParentEmail(dto.getParentEmail());
        entity.setBloodGroup(dto.getBloodGroup());
        entity.setAge(dto.getAge());
        entity.setStudentClass(dto.getStudentClass());
        entity.setDivision(dto.getDivision());
        entity.setMedium(dto.getMedium());
        entity.setAcademicYear(dto.getAcademicYear());
        return entity;
    }

    public static StudentResponseDTO toDTO(StudentModel entity) {
        StudentResponseDTO dto = new StudentResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setRollNumber(entity.getRollNumber());
        dto.setAdmission(entity.getAdmission());
        dto.setPresent(entity.getPresent());
        dto.setStatus(entity.getStatus());
        dto.setInBus(entity.getInBus());
        dto.setQrImageUrl(entity.getQrImageUrl());
        dto.setParentName(entity.getParentName());
        dto.setParentPhone(entity.getParentPhone());
        dto.setParentEmail(entity.getParentEmail());
        dto.setBloodGroup(entity.getBloodGroup());
        dto.setAge(entity.getAge());
        dto.setStudentClass(entity.getStudentClass());
        dto.setDivision(entity.getDivision());
        dto.setMedium(entity.getMedium());
        dto.setAcademicYear(entity.getAcademicYear());
        // optionally map payments and scans if needed
        return dto;
    }
}