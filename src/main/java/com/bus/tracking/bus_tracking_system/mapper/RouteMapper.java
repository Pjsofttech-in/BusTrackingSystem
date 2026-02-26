package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.*;
import com.bus.tracking.bus_tracking_system.model.Route;

public class RouteMapper {

    public static Route toEntity(RouteRequestDTO dto) {

        Route route = new Route();

        route.setRouteName(dto.getRouteName());
        route.setStartTime(dto.getStartTime());
        route.setStopTime(dto.getStopTime());
        route.setStartPoint(dto.getStartPoint());
        route.setEndPoint(dto.getEndPoint());
        route.setTotalDistanceKm(dto.getTotalDistanceKm());
        route.setEstimatedTimeMin(dto.getEstimatedTimeMin());
        route.setStatus(dto.getStatus());

        return route;
    }

    public static RouteResponseDTO toDTO(Route route) {

        RouteResponseDTO dto = new RouteResponseDTO();

        dto.setId(route.getId());
        dto.setRouteName(route.getRouteName());
        dto.setStartPoint(route.getStartPoint());
        dto.setEndPoint(route.getEndPoint());
        dto.setTotalDistanceKm(route.getTotalDistanceKm());
        dto.setEstimatedTimeMin(route.getEstimatedTimeMin());
        dto.setStatus(route.getStatus());

        return dto;
    }
}