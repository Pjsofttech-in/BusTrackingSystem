package com.app.bustracking.mapper;

import com.app.bustracking.dto.DivisionRequestDTO;
import com.app.bustracking.dto.DivisionResponseDTO;
import com.app.bustracking.model.DivisionModel;

public class DivisionMapper {

    public static DivisionModel toEntity(DivisionRequestDTO dto) {
        DivisionModel entity = new DivisionModel();
        entity.setDivisionName(dto.getDivisionName());
        return entity;
    }

    public static DivisionResponseDTO toDTO(DivisionModel entity) {
        DivisionResponseDTO dto = new DivisionResponseDTO();
        dto.setDivisionId(entity.getDivisionId());
        dto.setDivisionName(entity.getDivisionName());
        return dto;
    }
}