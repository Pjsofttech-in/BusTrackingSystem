package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.StudentRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.StudentResponseDTO;
import com.bus.tracking.bus_tracking_system.model.Student;

public class StudentMapper {

    // RequestDTO → Entity
    public static Student toEntity(StudentRequestDTO dto) {


        Student student = new Student();

        // Basic Info
        student.setName(dto.getName());
        student.setRollNumber(dto.getRollNumber());
        student.setAdmission(dto.getAdmission());
        student.setAge(dto.getAge());
        student.setBloodGroup(dto.getBloodGroup());

        // Parent Details
        student.setParentName(dto.getParentName());
        student.setParentPhone(dto.getParentPhone());
        student.setParentEmail(dto.getParentEmail());

        // Academic Details
        student.setStudentClass(dto.getStudentClass());
        student.setDivision(dto.getDivision());
        student.setMedium(dto.getMedium());
        student.setAcademicYear(dto.getAcademicYear());

        return student;
    }

    // Entity → ResponseDTO
    public static StudentResponseDTO toDTO(Student student) {



        StudentResponseDTO dto = new StudentResponseDTO();

        // Basic Info
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setRollNumber(student.getRollNumber());
        dto.setAdmission(student.getAdmission());
        dto.setAge(student.getAge());
        dto.setBloodGroup(student.getBloodGroup());

        // Parent Details
        dto.setParentName(student.getParentName());
        dto.setParentPhone(student.getParentPhone());
        dto.setParentEmail(student.getParentEmail());

        // Academic Details
        dto.setStudentClass(student.getStudentClass());
        dto.setDivision(student.getDivision());
        dto.setMedium(student.getMedium());
        dto.setAcademicYear(student.getAcademicYear());

        // Bus/System Fields
        dto.setQrImageUrl(student.getQrImageUrl());
        dto.setInBus(student.isInBus());
        dto.setStatus(student.getStatus());
        dto.setPresent(student.isPresent());

        return dto;
    }
}