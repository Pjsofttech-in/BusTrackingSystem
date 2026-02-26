package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.RouteRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.RouteResponseDTO;
import com.bus.tracking.bus_tracking_system.mapper.RouteMapper;
import com.bus.tracking.bus_tracking_system.model.Route;
import com.bus.tracking.bus_tracking_system.repository.RouteRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RouteService {

    private final RouteRepository repo;

    public RouteService(RouteRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public RouteResponseDTO addRoute(RouteRequestDTO dto) {

        Route route = RouteMapper.toEntity(dto);

        Route saved = repo.save(route);

        return RouteMapper.toDTO(saved);
    }

    // GET ALL
    public List<RouteResponseDTO> getAllRoutes() {

        return repo.findAll()
                .stream()
                .map(RouteMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public RouteResponseDTO getRouteById(Long id) {

        Route route = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        return RouteMapper.toDTO(route);
    }

    // UPDATE
    public RouteResponseDTO updateRoute(Long id,
                                        RouteRequestDTO dto) {

        Route existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        existing.setRouteName(dto.getRouteName());
        existing.setStartTime(dto.getStartTime());
        existing.setStopTime(dto.getStopTime());
        existing.setStartPoint(dto.getStartPoint());
        existing.setEndPoint(dto.getEndPoint());
        existing.setTotalDistanceKm(dto.getTotalDistanceKm());
        existing.setEstimatedTimeMin(dto.getEstimatedTimeMin());
        existing.setStatus(dto.getStatus());

        Route updated = repo.save(existing);

        return RouteMapper.toDTO(updated);
    }

    public void deleteRoute(Long id) {
        repo.deleteById(id);
    }
}