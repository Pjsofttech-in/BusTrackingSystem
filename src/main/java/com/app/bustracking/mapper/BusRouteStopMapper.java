package com.app.bustracking.mapper;

import com.app.bustracking.dto.BusRouteStopResponseDTO;
import com.app.bustracking.model.BusRouteStopModel;

public class BusRouteStopMapper {

    public static BusRouteStopResponseDTO toDTO(BusRouteStopModel entity) {
        BusRouteStopResponseDTO dto = new BusRouteStopResponseDTO();
        dto.setId(entity.getId());
        dto.setSequence(entity.getSequence());
        if (entity.getStop() != null) {
            dto.setStop(BusStopMapper.toDTO(entity.getStop()));
        }
        return dto;
    }
}