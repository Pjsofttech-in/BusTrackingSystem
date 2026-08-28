package com.app.bustracking.mapper;

import com.app.bustracking.dto.FeeStructureRequestDTO;
import com.app.bustracking.dto.FeeStructureResponseDTO;
import com.app.bustracking.model.FeeStructureModel;

public class FeeStructureMapper {

    public static FeeStructureModel toEntity(FeeStructureRequestDTO dto) {
        FeeStructureModel entity = new FeeStructureModel();
        entity.setAmountPerMonth(dto.getAmountPerMonth());
        entity.setAmountPerYear(dto.getAmountPerYear());
        entity.setAmountPerKm(dto.getAmountPerKm());
        entity.setDueDate(dto.getDueDate());
        return entity;
    }

    public static FeeStructureResponseDTO toDTO(FeeStructureModel entity) {
        FeeStructureResponseDTO dto = new FeeStructureResponseDTO();
        dto.setId(entity.getId());
        dto.setAmountPerMonth(entity.getAmountPerMonth());
        dto.setAmountPerYear(entity.getAmountPerYear());
        dto.setAmountPerKm(entity.getAmountPerKm());
        dto.setDueDate(entity.getDueDate());
        dto.setCreatedAt(entity.getCreatedAt());
        dto.setUpdatedAt(entity.getUpdatedAt());
        if (entity.getRoute() != null) {
            dto.setRouteId(entity.getRoute().getId());
            dto.setRouteName(entity.getRoute().getRouteName());
        }
        if (entity.getAcademicYear() != null) {
            dto.setAcademicYearId(entity.getAcademicYear().getId());
            dto.setAcademicYearName(entity.getAcademicYear().getYearName());
        }
        return dto;
    }
}