package com.app.bustracking.mapper;

import com.app.bustracking.dto.ClassRequestDTO;
import com.app.bustracking.dto.ClassResponseDTO;
import com.app.bustracking.model.ClassModel;

public class ClassMapper {

    public static ClassModel toEntity(ClassRequestDTO dto) {
        ClassModel entity = new ClassModel();
        entity.setName(dto.getName());
        return entity;
    }

    public static ClassResponseDTO toDTO(ClassModel entity) {
        ClassResponseDTO dto = new ClassResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        return dto;
    }
}