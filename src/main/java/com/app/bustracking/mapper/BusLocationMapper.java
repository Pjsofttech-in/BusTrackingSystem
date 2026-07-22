package com.app.bustracking.mapper;

import com.app.bustracking.dto.BusLocationRequestDTO;
import com.app.bustracking.dto.BusLocationResponseDTO;
import com.app.bustracking.model.BusLocationModel;
import com.app.bustracking.model.BusModel;
import com.app.bustracking.model.DirectionModel;

public class BusLocationMapper {

    public static BusLocationModel toEntity(BusLocationRequestDTO dto, BusModel bus, DirectionModel direction) {
        BusLocationModel entity = new BusLocationModel();
        entity.setBus(bus);
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setSpeed(dto.getSpeed());
        entity.setHeading(dto.getHeading());
        entity.setDirection(direction);
        entity.setAccuracy(dto.getAccuracy());
        entity.setStatus(dto.getStatus());
        entity.setTimestamp(dto.getTimestamp());
        return entity;
    }

    public static BusLocationResponseDTO toDTO(BusLocationModel entity) {
        BusLocationResponseDTO dto = new BusLocationResponseDTO();
        dto.setId(entity.getId());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setSpeed(entity.getSpeed());
        dto.setHeading(entity.getHeading());
        dto.setAccuracy(entity.getAccuracy());
        dto.setStatus(entity.getStatus());
        dto.setTimestamp(entity.getTimestamp());
        dto.setCreatedAt(entity.getCreatedAt());
        if (entity.getBus() != null) {
            dto.setBus(BusMapper.toDTO(entity.getBus()));
        }
        if (entity.getDirection() != null) {
            dto.setDirection(DirectionMapper.toDTO(entity.getDirection()));
        }
        return dto;
    }
}