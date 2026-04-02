package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.*;
import com.bus.tracking.bus_tracking_system.mapper.RouteMapper;
import com.bus.tracking.bus_tracking_system.model.Route;
import com.bus.tracking.bus_tracking_system.repository.RouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;

    //  SAVE ROUTE WITH STOPS
    public RouteResponseDTO saveRoute(RouteRequestDTO dto) {

        Route route = RouteMapper.toEntity(dto);

        Route savedRoute = routeRepository.save(route);

        return RouteMapper.toDTO(savedRoute);
    }

    // GET ROUTE BY ID
    public RouteResponseDTO getRoute(Long id) {

        Route route = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        return RouteMapper.toDTO(route);
    }

    // GET ALL ROUTES
    public List<RouteResponseDTO> getAllRoutes() {
        return routeRepository.findAll()
                .stream()
                .map(RouteMapper::toDTO)
                .toList();
    }

    // DELETE ROUTE
    public void deleteRoute(Long id) {
        routeRepository.deleteById(id);
    }
}