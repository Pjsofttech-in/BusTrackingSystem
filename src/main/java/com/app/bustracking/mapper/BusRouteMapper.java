package com.app.bustracking.mapper;

import com.app.bustracking.dto.BusRouteRequestDTO;
import com.app.bustracking.dto.BusRouteResponseDTO;
import com.app.bustracking.model.*;

import java.util.stream.Collectors;

public class BusRouteMapper {

    public static BusRouteModel toEntity(BusRouteRequestDTO dto,
                                         BusStopModel startStop,
                                         BusStopModel endStop) {
        BusRouteModel entity = new BusRouteModel();
        entity.setRouteName(dto.getRouteName());
        entity.setDescription(dto.getDescription());
        entity.setStartStop(startStop);
        entity.setEndStop(endStop);
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setTotalDistanceKm(dto.getTotalDistanceKm());
        entity.setEstimatedTimeMin(dto.getEstimatedTimeMin());
        entity.setStatus(dto.getStatus());
        // ❌ Removed: bus, driver, conductor
        return entity;
    }

    public static BusRouteResponseDTO toDTO(BusRouteModel entity) {
        BusRouteResponseDTO dto = new BusRouteResponseDTO();
        dto.setId(entity.getId());
        dto.setRouteName(entity.getRouteName());
        dto.setDescription(entity.getDescription());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setTotalDistanceKm(entity.getTotalDistanceKm());
        dto.setEstimatedTimeMin(entity.getEstimatedTimeMin());
        dto.setStatus(entity.getStatus());
        dto.setCreatedAt(entity.getCreatedAt());

        // Start Stop
        if (entity.getStartStop() != null) {
            dto.setStartStopId(entity.getStartStop().getId());
            dto.setStartStopName(entity.getStartStop().getStopName());
        }
        // End Stop
        if (entity.getEndStop() != null) {
            dto.setEndStopId(entity.getEndStop().getId());
            dto.setEndStopName(entity.getEndStop().getStopName());
        }

        // ❌ Removed: bus, driver, conductor mapping

        // Stops (ordered)
        if (entity.getStops() != null) {
            dto.setStops(entity.getStops().stream()
                    .map(BusRouteStopMapper::toDTO)
                    .collect(Collectors.toList()));
        }

        return dto;
    }
}