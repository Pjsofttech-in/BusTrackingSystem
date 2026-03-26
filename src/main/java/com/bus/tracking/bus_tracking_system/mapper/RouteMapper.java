package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.RouteRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.RouteResponseDTO;
import com.bus.tracking.bus_tracking_system.model.Route;

public class RouteMapper {

    // RequestDTO → Entity
    public static Route toEntity(RouteRequestDTO dto) {

        if (dto == null) return null;

        Route route = new Route();

        route.setRouteName(dto.getRouteName());
        route.setStartTime(dto.getStartTime());
        route.setStopTime(dto.getStopTime());
        route.setStartPoint(dto.getStartPoint());
        route.setEndPoint(dto.getEndPoint());
        route.setTotalDistanceKm(dto.getTotalDistanceKm());
        route.setEstimatedTimeMin(dto.getEstimatedTimeMin());
        route.setStatus(dto.getStatus());

        // ✅ IMPORTANT (you missed this)
        route.setBusStop(dto.getBusStop());

        return route;
    }

    // Entity → ResponseDTO
    public static RouteResponseDTO toDTO(Route route) {

        RouteResponseDTO dto = new RouteResponseDTO();

        dto.setId(route.getId());
        dto.setRouteName(route.getRouteName());

        //TIME
        dto.setStartTime(route.getStartTime());
        dto.setStopTime(route.getStopTime());

        dto.setStartPoint(route.getStartPoint());
        dto.setEndPoint(route.getEndPoint());
        dto.setTotalDistanceKm(route.getTotalDistanceKm());
        dto.setEstimatedTimeMin(route.getEstimatedTimeMin());
        dto.setStatus(route.getStatus());

        // BUS STOP IMPORTANT
        dto.setBusStop(route.getBusStop());

        return dto;
    }
}