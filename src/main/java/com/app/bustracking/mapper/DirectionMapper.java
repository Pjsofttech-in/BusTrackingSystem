package com.app.bustracking.mapper;

import com.app.bustracking.dto.DirectionRequestDTO;
import com.app.bustracking.dto.DirectionResponseDTO;
import com.app.bustracking.model.DirectionModel;

public class DirectionMapper {

    public static DirectionModel toEntity(DirectionRequestDTO dto) {
        DirectionModel entity = new DirectionModel();
        entity.setName(dto.getName());
        entity.setAbbreviation(dto.getAbbreviation());
        entity.setMinDegrees(dto.getMinDegrees());
        entity.setMaxDegrees(dto.getMaxDegrees());
        entity.setDescription(dto.getDescription());
        return entity;
    }

    public static DirectionResponseDTO toDTO(DirectionModel entity) {
        DirectionResponseDTO dto = new DirectionResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setAbbreviation(entity.getAbbreviation());
        dto.setMinDegrees(entity.getMinDegrees());
        dto.setMaxDegrees(entity.getMaxDegrees());
        dto.setDescription(entity.getDescription());
        return dto;
    }
}