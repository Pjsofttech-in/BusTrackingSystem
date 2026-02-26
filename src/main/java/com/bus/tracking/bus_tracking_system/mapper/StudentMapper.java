package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.StudentRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.StudentResponseDTO;
import com.bus.tracking.bus_tracking_system.model.Student;

public class StudentMapper {

    // RequestDTO → Entity
    public static Student toEntity(StudentRequestDTO dto) {

        Student student = new Student();

        student.setName(dto.getName());
        student.setRollNumber(dto.getRollNumber());
        student.setAdmission(dto.getAdmission());
        student.setAge(dto.getAge());
        student.setBloodGroup(dto.getBloodGroup());

        student.setParentName(dto.getParentName());
        student.setParentPhone(dto.getParentPhone());
        student.setParentEmail(dto.getParentEmail());

        student.setStudentClass(dto.getStudentClass());
        student.setDivision(dto.getDivision());
        student.setMedium(dto.getMedium());
        student.setAcademicYear(dto.getAcademicYear());

        return student;
    }

    // Entity → ResponseDTO
    public static StudentResponseDTO toDTO(Student student) {

        StudentResponseDTO dto = new StudentResponseDTO();

        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setRollNumber(student.getRollNumber());
        dto.setQrImageUrl(student.getQrImageUrl());
        dto.setInBus(student.isInBus());
        dto.setStatus(student.getStatus());

        return dto;
    }
}