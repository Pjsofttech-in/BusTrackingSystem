package com.app.bustracking.mapper;

import com.app.bustracking.dto.MediumRequestDTO;
import com.app.bustracking.dto.MediumResponseDTO;
import com.app.bustracking.model.MediumModel;

public class MediumMapper {

    public static MediumModel toEntity(MediumRequestDTO dto) {
        MediumModel entity = new MediumModel();
        entity.setMediumName(dto.getMediumName());
        return entity;
    }

    public static MediumResponseDTO toDTO(MediumModel entity) {
        MediumResponseDTO dto = new MediumResponseDTO();
        dto.setId(entity.getId());
        dto.setMediumName(entity.getMediumName());
        return dto;
    }
}