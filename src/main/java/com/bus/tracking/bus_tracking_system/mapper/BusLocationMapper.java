package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.BusLocationRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusLocationResponseDTO;
import com.bus.tracking.bus_tracking_system.model.Bus;
import com.bus.tracking.bus_tracking_system.model.BusLocation;

public class BusLocationMapper {

    // DTO → Entity
    public static BusLocation toEntity(BusLocationRequestDTO dto, Bus bus) {

        BusLocation location = new BusLocation();

        location.setBus(bus);
        location.setLatitude(dto.getLatitude());
        location.setLongitude(dto.getLongitude());
        location.setSpeedKmph(dto.getSpeedKmph());
        location.setDirection(dto.getDirection());

        return location;
    }

    // Entity → DTO
    public static BusLocationResponseDTO toDTO(BusLocation location) {

        BusLocationResponseDTO dto = new BusLocationResponseDTO();

        dto.setId(location.getId());
        dto.setBusId(location.getBus().getId());
        dto.setLatitude(location.getLatitude());
        dto.setLongitude(location.getLongitude());
        dto.setSpeedKmph(location.getSpeedKmph());
        dto.setDirection(location.getDirection());
        dto.setRecordedAt(location.getRecordedAt());

        return dto;
    }
}