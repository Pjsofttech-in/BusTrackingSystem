package com.app.bustracking.service;

import com.app.bustracking.dto.BusRouteRequestDTO;
import com.app.bustracking.dto.BusRouteResponseDTO;
import com.app.bustracking.mapper.BusRouteMapper;
import com.app.bustracking.model.*;
import com.app.bustracking.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusRouteService {

    private static final Logger log = LoggerFactory.getLogger(BusRouteService.class);

    private final BusRouteRepository routeRepository;
    private final BusStopRepository busStopRepository;
    private final BusRouteStopRepository routeStopRepository;
    private final BusTripRepository tripRepository;   // NEW – for handling references

    public BusRouteService(BusRouteRepository routeRepository,
                           BusStopRepository busStopRepository,
                           BusRouteStopRepository routeStopRepository,
                           BusTripRepository tripRepository) {
        this.routeRepository = routeRepository;
        this.busStopRepository = busStopRepository;
        this.routeStopRepository = routeStopRepository;
        this.tripRepository = tripRepository;
    }

    @Transactional
    public BusRouteResponseDTO create(BusRouteRequestDTO dto) {
        log.info("Creating bus route: {}", dto);

        if (dto.getRouteName() == null || dto.getRouteName().trim().isEmpty()) {
            throw new IllegalArgumentException("Route name is required");
        }

        BusStopModel startStop = null;
        BusStopModel endStop = null;

        if (dto.getStartStopId() != null) {
            startStop = busStopRepository.findById(dto.getStartStopId())
                    .orElseThrow(() -> new IllegalArgumentException("Start stop not found: " + dto.getStartStopId()));
        }
        if (dto.getEndStopId() != null) {
            endStop = busStopRepository.findById(dto.getEndStopId())
                    .orElseThrow(() -> new IllegalArgumentException("End stop not found: " + dto.getEndStopId()));
        }

        BusRouteModel route = BusRouteMapper.toEntity(dto, startStop, endStop);
        BusRouteModel savedRoute = routeRepository.save(route);

        // Add stops if provided
        if (dto.getStopIds() != null && !dto.getStopIds().isEmpty()) {
            List<BusRouteStopModel> stops = new ArrayList<>();
            int seq = 0;
            for (Long stopId : dto.getStopIds()) {
                BusStopModel stop = busStopRepository.findById(stopId)
                        .orElseThrow(() -> new IllegalArgumentException("Stop not found: " + stopId));
                BusRouteStopModel routeStop = new BusRouteStopModel();
                routeStop.setRoute(savedRoute);
                routeStop.setStop(stop);
                routeStop.setSequence(seq++);
                stops.add(routeStop);
            }
            routeStopRepository.saveAll(stops);
            savedRoute.setStops(stops);
        }

        return routeRepository.findByIdWithDetails(savedRoute.getId())
                .map(BusRouteMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Failed to load created route"));
    }

    @Transactional(readOnly = true)
    public List<BusRouteResponseDTO> getAll() {
        return routeRepository.findAllWithDetails().stream()
                .map(BusRouteMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public BusRouteResponseDTO getById(Long id) {
        return routeRepository.findByIdWithDetails(id)
                .map(BusRouteMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public BusRouteResponseDTO update(Long id, BusRouteRequestDTO dto) {
        log.info("Updating bus route ID {} with data: {}", id, dto);

        BusRouteModel route = routeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Route not found with ID: " + id));

        // Update only non‑null fields
        if (dto.getRouteName() != null && !dto.getRouteName().trim().isEmpty()) {
            route.setRouteName(dto.getRouteName().trim());
        }
        if (dto.getDescription() != null) {
            route.setDescription(dto.getDescription());
        }
        if (dto.getStartStopId() != null) {
            BusStopModel startStop = busStopRepository.findById(dto.getStartStopId())
                    .orElseThrow(() -> new IllegalArgumentException("Start stop not found: " + dto.getStartStopId()));
            route.setStartStop(startStop);
        } else {
            route.setStartStop(null);
        }
        if (dto.getEndStopId() != null) {
            BusStopModel endStop = busStopRepository.findById(dto.getEndStopId())
                    .orElseThrow(() -> new IllegalArgumentException("End stop not found: " + dto.getEndStopId()));
            route.setEndStop(endStop);
        } else {
            route.setEndStop(null);
        }
        if (dto.getStartTime() != null) {
            route.setStartTime(dto.getStartTime());
        }
        if (dto.getEndTime() != null) {
            route.setEndTime(dto.getEndTime());
        }
        if (dto.getTotalDistanceKm() != null) {
            route.setTotalDistanceKm(dto.getTotalDistanceKm());
        }
        if (dto.getEstimatedTimeMin() != null) {
            route.setEstimatedTimeMin(dto.getEstimatedTimeMin());
        }
        if (dto.getStatus() != null) {
            route.setStatus(dto.getStatus());
        }

        // Handle stops – clear old and add new
        if (dto.getStopIds() != null) {
            // First delete all existing stops
            routeStopRepository.deleteAll(route.getStops());
            route.getStops().clear();

            if (!dto.getStopIds().isEmpty()) {
                List<BusRouteStopModel> newStops = new ArrayList<>();
                int seq = 0;
                for (Long stopId : dto.getStopIds()) {
                    BusStopModel stop = busStopRepository.findById(stopId)
                            .orElseThrow(() -> new IllegalArgumentException("Stop not found: " + stopId));
                    BusRouteStopModel routeStop = new BusRouteStopModel();
                    routeStop.setRoute(route);
                    routeStop.setStop(stop);
                    routeStop.setSequence(seq++);
                    newStops.add(routeStop);
                }
                routeStopRepository.saveAll(newStops);
                route.setStops(newStops);
            }
        }

        BusRouteModel updated = routeRepository.save(route);
        return routeRepository.findByIdWithDetails(updated.getId())
                .map(BusRouteMapper::toDTO)
                .orElseThrow(() -> new RuntimeException("Failed to load updated route"));
    }

    @Transactional
    public void delete(Long id) {
        log.info("Deleting bus route ID {}", id);

        // Check if route exists
        BusRouteModel route = routeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Route not found with ID: " + id));

        // Delete all associated trips (set route to null first to avoid constraint violation)
        List<BusTripModel> trips = tripRepository.findByRouteId(id);
        if (!trips.isEmpty()) {
            log.info("Deleting {} trips referencing route ID {}", trips.size(), id);
            tripRepository.deleteAll(trips);
        }

        // Delete all stops (already cascaded, but we'll clear explicitly)
        routeStopRepository.deleteAll(route.getStops());
        route.getStops().clear();

        // Now delete the route
        routeRepository.delete(route);
    }
}