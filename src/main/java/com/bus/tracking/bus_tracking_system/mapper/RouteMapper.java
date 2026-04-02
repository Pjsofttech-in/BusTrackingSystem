package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.*;
import com.bus.tracking.bus_tracking_system.model.*;

import java.util.List;
import java.util.stream.Collectors;

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

        if (dto.getStops() != null) {
            List<RouteStop> stops = dto.getStops().stream().map(s -> {
                RouteStop stop = new RouteStop();
                stop.setStopName(s.getStopName());
                stop.setArrivalTime(s.getArrivalTime());
                stop.setRoute(route);
                return stop;
            }).collect(Collectors.toList());

            route.setStops(stops);
        }

        return route;
    }

    public static RouteResponseDTO toDTO(Route route) {

        RouteResponseDTO dto = new RouteResponseDTO();

        dto.setId(route.getId());
        dto.setRouteName(route.getRouteName());
        dto.setStartTime(route.getStartTime());
        dto.setStopTime(route.getStopTime());
        dto.setStartPoint(route.getStartPoint());
        dto.setEndPoint(route.getEndPoint());
        dto.setTotalDistanceKm(route.getTotalDistanceKm());
        dto.setEstimatedTimeMin(route.getEstimatedTimeMin());
        dto.setStatus(route.getStatus());

        if (route.getStops() != null) {
            List<RouteStopDTO> stops = route.getStops().stream().map(s -> {
                RouteStopDTO stopDTO = new RouteStopDTO();
                stopDTO.setStopName(s.getStopName());
                stopDTO.setArrivalTime(s.getArrivalTime());
                return stopDTO;
            }).collect(Collectors.toList());

            dto.setStops(stops);
        }

        return dto;
    }
}