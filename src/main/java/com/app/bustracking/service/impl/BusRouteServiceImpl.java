package com.app.bustracking.service.impl;

import com.app.bustracking.Mapper.BusRouteMapper;
import com.app.bustracking.Model.*;
import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.repository.*;
import com.app.bustracking.Request.BusRouteRequest;
import com.app.bustracking.Response.BusRouteResponse;
import com.app.bustracking.service.BusRouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusRouteServiceImpl implements BusRouteService {

    private final BusRouteRepository routeRepository;
    private final BusRepository busRepository;
    private final DriverRepository driverRepository;
    private final ConductorRepository conductorRepository;
    private final BusStopRepository busStopRepository;
    private final BusRouteMapper mapper;

    @Override
    public List<BusRouteResponse> getAll() {
        return routeRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BusRouteResponse getById(Long id) {
        BusRouteModel route = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus route not found with id: " + id));
        return mapper.toResponse(route);
    }

    @Override
    @Transactional
    public BusRouteResponse create(BusRouteRequest request) {
        BusRouteModel route = mapper.toModel(request);

        // Set new relations
        if (request.startStopId() != null) {
            BusStopModel start = busStopRepository.findById(request.startStopId())
                    .orElseThrow(() -> new ResourceNotFoundException("Start stop not found with id: " + request.startStopId()));
            route.setStartStop(start);
        }
        if (request.endStopId() != null) {
            BusStopModel end = busStopRepository.findById(request.endStopId())
                    .orElseThrow(() -> new ResourceNotFoundException("End stop not found with id: " + request.endStopId()));
            route.setEndStop(end);
        }

        // Existing relations
        if (request.busId() != null) {
            BusModel bus = busRepository.findById(request.busId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id: " + request.busId()));
            route.setBus(bus);
        }
        if (request.driverId() != null) {
            DriverModel driver = driverRepository.findById(request.driverId())
                    .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + request.driverId()));
            route.setDriver(driver);
        }
        if (request.conductorId() != null) {
            ConductorModel conductor = conductorRepository.findById(request.conductorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Conductor not found with id: " + request.conductorId()));
            route.setConductor(conductor);
        }

        // Build ordered stops
        List<Long> stopIds = request.stopIds();
        if (stopIds == null || stopIds.isEmpty()) {
            throw new IllegalArgumentException("At least one stop is required");
        }
        for (int i = 0; i < stopIds.size(); i++) {
            Long stopId = stopIds.get(i);
            BusStopModel stop = busStopRepository.findById(stopId)
                    .orElseThrow(() -> new ResourceNotFoundException("Bus stop not found with id: " + stopId));
            BusRouteStopModel routeStop = new BusRouteStopModel();
            routeStop.setRoute(route);
            routeStop.setStop(stop);
            routeStop.setSequence(i + 1);
            route.getStops().add(routeStop);
        }

        BusRouteModel saved = routeRepository.save(route);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public BusRouteResponse update(Long id, BusRouteRequest request) {
        BusRouteModel existing = routeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus route not found with id: " + id));

        // Update scalar fields
        existing.setRouteName(request.routeName());
        existing.setDescription(request.description());
        existing.setStartTime(request.startTime());
        existing.setEndTime(request.endTime());
        existing.setTotalDistanceKm(request.totalDistanceKm());
        existing.setEstimatedTimeMin(request.estimatedTimeMin());
        existing.setStatus(request.status());

        // Update stop relations
        existing.setStartStop(null);
        existing.setEndStop(null);
        if (request.startStopId() != null) {
            BusStopModel start = busStopRepository.findById(request.startStopId())
                    .orElseThrow(() -> new ResourceNotFoundException("Start stop not found with id: " + request.startStopId()));
            existing.setStartStop(start);
        }
        if (request.endStopId() != null) {
            BusStopModel end = busStopRepository.findById(request.endStopId())
                    .orElseThrow(() -> new ResourceNotFoundException("End stop not found with id: " + request.endStopId()));
            existing.setEndStop(end);
        }

        // Update bus/driver/conductor
        existing.setBus(null);
        existing.setDriver(null);
        existing.setConductor(null);
        if (request.busId() != null) {
            BusModel bus = busRepository.findById(request.busId())
                    .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id: " + request.busId()));
            existing.setBus(bus);
        }
        if (request.driverId() != null) {
            DriverModel driver = driverRepository.findById(request.driverId())
                    .orElseThrow(() -> new ResourceNotFoundException("Driver not found with id: " + request.driverId()));
            existing.setDriver(driver);
        }
        if (request.conductorId() != null) {
            ConductorModel conductor = conductorRepository.findById(request.conductorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Conductor not found with id: " + request.conductorId()));
            existing.setConductor(conductor);
        }

        // Replace ordered stops
        existing.getStops().clear();
        List<Long> stopIds = request.stopIds();
        if (stopIds == null || stopIds.isEmpty()) {
            throw new IllegalArgumentException("At least one stop is required");
        }
        for (int i = 0; i < stopIds.size(); i++) {
            Long stopId = stopIds.get(i);
            BusStopModel stop = busStopRepository.findById(stopId)
                    .orElseThrow(() -> new ResourceNotFoundException("Bus stop not found with id: " + stopId));
            BusRouteStopModel routeStop = new BusRouteStopModel();
            routeStop.setRoute(existing);
            routeStop.setStop(stop);
            routeStop.setSequence(i + 1);
            existing.getStops().add(routeStop);
        }

        BusRouteModel updated = routeRepository.save(existing);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!routeRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bus route not found with id: " + id);
        }
        routeRepository.deleteById(id);
    }
}