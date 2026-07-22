package com.app.bustracking.mapper;

import com.app.bustracking.dto.AcademicYearRequestDTO;
import com.app.bustracking.dto.AcademicYearResponseDTO;
import com.app.bustracking.model.AcademicYearModel;

public class AcademicYearMapper {

    public static AcademicYearModel toEntity(AcademicYearRequestDTO dto) {
        AcademicYearModel entity = new AcademicYearModel();
        entity.setYearName(dto.getYearName());
        return entity;
    }

    public static AcademicYearResponseDTO toDTO(AcademicYearModel entity) {
        AcademicYearResponseDTO dto = new AcademicYearResponseDTO();
        dto.setId(entity.getId());
        dto.setYearName(entity.getYearName());
        return dto;
    }
}