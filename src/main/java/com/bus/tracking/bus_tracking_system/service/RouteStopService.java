package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.RouteStopDTO;
import com.bus.tracking.bus_tracking_system.mapper.RouteStopMapper;
import com.bus.tracking.bus_tracking_system.model.Route;
import com.bus.tracking.bus_tracking_system.model.RouteStop;
import com.bus.tracking.bus_tracking_system.repository.RouteRepository;
import com.bus.tracking.bus_tracking_system.repository.RouteStopRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RouteStopService {

    private final RouteStopRepository routeStopRepository;
    private final RouteRepository routeRepository;

    // ADD STOP TO ROUTE
    public RouteStopDTO addStop(Long routeId, RouteStopDTO dto) {

        Route route = routeRepository.findById(routeId)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        RouteStop stop = RouteStopMapper.toEntity(dto);
        stop.setRoute(route);

        RouteStop saved = routeStopRepository.save(stop);

        return RouteStopMapper.toDTO(saved);
    }

    //  GET ALL STOPS
    public List<RouteStopDTO> getAllStops() {

        return routeStopRepository.findAll()
                .stream()
                .map(RouteStopMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET STOP BY ID
    public RouteStopDTO getStop(Long id) {

        RouteStop stop = routeStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Stop not found"));

        return RouteStopMapper.toDTO(stop);
    }

    //  DELETE STOP
    public void deleteStop(Long id) {
        routeStopRepository.deleteById(id);
    }
}