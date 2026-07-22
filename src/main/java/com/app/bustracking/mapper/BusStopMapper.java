package com.app.bustracking.mapper;

import com.app.bustracking.dto.BusStopRequestDTO;
import com.app.bustracking.dto.BusStopResponseDTO;
import com.app.bustracking.model.BusModel;
import com.app.bustracking.model.BusStopModel;

public class BusStopMapper {

    public static BusStopModel toEntity(BusStopRequestDTO dto, BusModel bus) {
        BusStopModel entity = new BusStopModel();
        entity.setBus(bus);
        entity.setStopName(dto.getStopName());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setReached(dto.getReached() != null ? dto.getReached() : false);
        return entity;
    }

    public static BusStopResponseDTO toDTO(BusStopModel entity) {
        BusStopResponseDTO dto = new BusStopResponseDTO();
        dto.setId(entity.getId());
        dto.setStopName(entity.getStopName());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setReached(entity.isReached());
        dto.setReachedAt(entity.getReachedAt());
        if (entity.getBus() != null) {
            dto.setBus(BusMapper.toDTO(entity.getBus()));
        }
        return dto;
    }
}