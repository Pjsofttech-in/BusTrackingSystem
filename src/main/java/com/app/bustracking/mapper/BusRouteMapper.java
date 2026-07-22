package com.app.bustracking.mapper;

import com.app.bustracking.dto.BusRouteRequestDTO;
import com.app.bustracking.dto.BusRouteResponseDTO;
import com.app.bustracking.model.*;

import java.util.stream.Collectors;

public class BusRouteMapper {

    public static BusRouteModel toEntity(BusRouteRequestDTO dto,
                                         BusModel bus,
                                         DriverModel driver,
                                         ConductorModel conductor,
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
        entity.setBus(bus);
        entity.setDriver(driver);
        entity.setConductor(conductor);
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

        if (entity.getStartStop() != null) {
            dto.setStartStop(BusStopMapper.toDTO(entity.getStartStop()));
        }
        if (entity.getEndStop() != null) {
            dto.setEndStop(BusStopMapper.toDTO(entity.getEndStop()));
        }
        if (entity.getBus() != null) {
            dto.setBus(BusMapper.toDTO(entity.getBus()));
        }
        if (entity.getDriver() != null) {
            dto.setDriver(DriverMapper.toDTO(entity.getDriver()));
        }
        if (entity.getConductor() != null) {
            dto.setConductor(ConductorMapper.toDTO(entity.getConductor()));
        }
        if (entity.getStops() != null) {
            dto.setStops(entity.getStops().stream()
                    .map(BusRouteStopMapper::toDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}