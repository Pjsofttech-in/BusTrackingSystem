package com.app.bustracking.service;

import com.app.bustracking.dto.BusRouteRequestDTO;
import com.app.bustracking.dto.BusRouteResponseDTO;
import com.app.bustracking.mapper.BusRouteMapper;
import com.app.bustracking.model.*;
import com.app.bustracking.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusRouteService {

    private final BusRouteRepository routeRepository;
    private final BusRepository busRepository;
    private final DriverRepository driverRepository;
    private final ConductorRepository conductorRepository;
    private final BusStopRepository busStopRepository;
    private final BusRouteStopRepository routeStopRepository;

    public BusRouteService(BusRouteRepository routeRepository,
                           BusRepository busRepository,
                           DriverRepository driverRepository,
                           ConductorRepository conductorRepository,
                           BusStopRepository busStopRepository,
                           BusRouteStopRepository routeStopRepository) {
        this.routeRepository = routeRepository;
        this.busRepository = busRepository;
        this.driverRepository = driverRepository;
        this.conductorRepository = conductorRepository;
        this.busStopRepository = busStopRepository;
        this.routeStopRepository = routeStopRepository;
    }

    @Transactional
    public BusRouteResponseDTO create(BusRouteRequestDTO dto) {
        BusModel bus = dto.getBusId() != null ? busRepository.findById(dto.getBusId()).orElse(null) : null;
        DriverModel driver = dto.getDriverId() != null ? driverRepository.findById(dto.getDriverId()).orElse(null) : null;
        ConductorModel conductor = dto.getConductorId() != null ? conductorRepository.findById(dto.getConductorId()).orElse(null) : null;
        BusStopModel startStop = dto.getStartStopId() != null ? busStopRepository.findById(dto.getStartStopId()).orElse(null) : null;
        BusStopModel endStop = dto.getEndStopId() != null ? busStopRepository.findById(dto.getEndStopId()).orElse(null) : null;

        BusRouteModel route = BusRouteMapper.toEntity(dto, bus, driver, conductor, startStop, endStop);

        // Save route first to get ID
        BusRouteModel savedRoute = routeRepository.save(route);

        // Add stops if provided
        if (dto.getStopIds() != null && !dto.getStopIds().isEmpty()) {
            List<BusRouteStopModel> stops = new ArrayList<>();
            int seq = 0;
            for (Long stopId : dto.getStopIds()) {
                BusStopModel stop = busStopRepository.findById(stopId)
                        .orElseThrow(() -> new RuntimeException("Stop not found: " + stopId));
                BusRouteStopModel routeStop = new BusRouteStopModel();
                routeStop.setRoute(savedRoute);
                routeStop.setStop(stop);
                routeStop.setSequence(seq++);
                stops.add(routeStop);
            }
            routeStopRepository.saveAll(stops);
            savedRoute.setStops(stops);
        }

        return BusRouteMapper.toDTO(savedRoute);
    }

    public List<BusRouteResponseDTO> getAll() {
        return routeRepository.findAll().stream()
                .map(BusRouteMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BusRouteResponseDTO getById(Long id) {
        return routeRepository.findById(id)
                .map(BusRouteMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public BusRouteResponseDTO update(Long id, BusRouteRequestDTO dto) {
        BusRouteModel route = routeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Route not found"));

        BusModel bus = dto.getBusId() != null ? busRepository.findById(dto.getBusId()).orElse(null) : null;
        DriverModel driver = dto.getDriverId() != null ? driverRepository.findById(dto.getDriverId()).orElse(null) : null;
        ConductorModel conductor = dto.getConductorId() != null ? conductorRepository.findById(dto.getConductorId()).orElse(null) : null;
        BusStopModel startStop = dto.getStartStopId() != null ? busStopRepository.findById(dto.getStartStopId()).orElse(null) : null;
        BusStopModel endStop = dto.getEndStopId() != null ? busStopRepository.findById(dto.getEndStopId()).orElse(null) : null;

        route.setRouteName(dto.getRouteName());
        route.setDescription(dto.getDescription());
        route.setStartStop(startStop);
        route.setEndStop(endStop);
        route.setStartTime(dto.getStartTime());
        route.setEndTime(dto.getEndTime());
        route.setTotalDistanceKm(dto.getTotalDistanceKm());
        route.setEstimatedTimeMin(dto.getEstimatedTimeMin());
        route.setStatus(dto.getStatus());
        route.setBus(bus);
        route.setDriver(driver);
        route.setConductor(conductor);

        // Update stops: delete existing and add new
        if (dto.getStopIds() != null) {
            routeStopRepository.deleteAll(route.getStops());
            route.getStops().clear();
            List<BusRouteStopModel> newStops = new ArrayList<>();
            int seq = 0;
            for (Long stopId : dto.getStopIds()) {
                BusStopModel stop = busStopRepository.findById(stopId)
                        .orElseThrow(() -> new RuntimeException("Stop not found: " + stopId));
                BusRouteStopModel routeStop = new BusRouteStopModel();
                routeStop.setRoute(route);
                routeStop.setStop(stop);
                routeStop.setSequence(seq++);
                newStops.add(routeStop);
            }
            routeStopRepository.saveAll(newStops);
            route.setStops(newStops);
        }

        BusRouteModel updated = routeRepository.save(route);
        return BusRouteMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        routeRepository.deleteById(id);
    }
}