package com.app.bustracking.mapper;

import com.app.bustracking.dto.BusTripRequestDTO;
import com.app.bustracking.dto.BusTripResponseDTO;
import com.app.bustracking.model.*;

public class BusTripMapper {

    public static BusTripModel toEntity(BusTripRequestDTO dto,
                                        BusModel bus,
                                        BusRouteModel route,
                                        DriverModel driver,
                                        ConductorModel conductor) {
        BusTripModel entity = new BusTripModel();
        entity.setBus(bus);
        entity.setRoute(route);
        entity.setDriver(driver);
        entity.setConductor(conductor);
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setTripStatus(dto.getTripStatus());
        return entity;
    }

    public static BusTripResponseDTO toDTO(BusTripModel entity) {
        BusTripResponseDTO dto = new BusTripResponseDTO();
        dto.setId(entity.getId());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setTripStatus(entity.getTripStatus());
        dto.setCreatedAt(entity.getCreatedAt());

        // ✅ Set ID fields (null‑safe)
        dto.setBusId(entity.getBus() != null ? entity.getBus().getId() : null);
        dto.setRouteId(entity.getRoute() != null ? entity.getRoute().getId() : null);
        dto.setDriverId(entity.getDriver() != null ? entity.getDriver().getId() : null);
        dto.setConductorId(entity.getConductor() != null ? entity.getConductor().getId() : null);

        // Map nested objects
        if (entity.getBus() != null) {
            dto.setBus(BusMapper.toDTO(entity.getBus()));
        }
        if (entity.getRoute() != null) {
            dto.setRoute(BusRouteMapper.toDTO(entity.getRoute()));
        }
        if (entity.getDriver() != null) {
            dto.setDriver(DriverMapper.toDTO(entity.getDriver()));
        }
        if (entity.getConductor() != null) {
            dto.setConductor(ConductorMapper.toDTO(entity.getConductor()));
        }
        return dto;
    }
}