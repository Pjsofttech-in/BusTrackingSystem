package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.RouteStopDTO;
import com.bus.tracking.bus_tracking_system.model.RouteStop;

public class RouteStopMapper {

    // DTO → Entity
    public static RouteStop toEntity(RouteStopDTO dto) {

        RouteStop stop = new RouteStop();

        stop.setStopName(dto.getStopName());
        stop.setArrivalTime(dto.getArrivalTime());

        return stop;
    }

    // Entity → DTO
    public static RouteStopDTO toDTO(RouteStop stop) {

        RouteStopDTO dto = new RouteStopDTO();

        dto.setStopName(stop.getStopName());
        dto.setArrivalTime(stop.getArrivalTime());

        return dto;
    }
}