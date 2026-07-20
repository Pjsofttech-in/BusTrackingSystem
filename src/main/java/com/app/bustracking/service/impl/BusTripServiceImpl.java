package com.app.bustracking.service.impl;

import com.app.bustracking.Mapper.BusTripMapper;
import com.app.bustracking.Model.*;
import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.repository.*;
import com.app.bustracking.Request.BusTripRequest;
import com.app.bustracking.Response.BusTripResponse;
import com.app.bustracking.service.BusTripService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusTripServiceImpl implements BusTripService {

    private final BusTripRepository tripRepository;
    private final BusRepository busRepository;
    private final BusRouteRepository routeRepository;
    private final DriverRepository driverRepository;
    private final ConductorRepository conductorRepository;
    private final BusTripMapper mapper;

    @Override
    public List<BusTripResponse> getAll() {
        return tripRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BusTripResponse getById(Long id) {
        BusTripModel trip = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found: " + id));
        return mapper.toResponse(trip);
    }

    @Override
    @Transactional
    public BusTripResponse create(BusTripRequest request) {
        BusTripModel trip = mapper.toModel(request);

        trip.setBus(busRepository.findById(request.busId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found: " + request.busId())));
        trip.setRoute(routeRepository.findById(request.routeId())
                .orElseThrow(() -> new ResourceNotFoundException("Route not found: " + request.routeId())));
        if (request.driverId() != null) {
            trip.setDriver(driverRepository.findById(request.driverId())
                    .orElseThrow(() -> new ResourceNotFoundException("Driver not found: " + request.driverId())));
        }
        if (request.conductorId() != null) {
            trip.setConductor(conductorRepository.findById(request.conductorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Conductor not found: " + request.conductorId())));
        }

        BusTripModel saved = tripRepository.save(trip);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public BusTripResponse update(Long id, BusTripRequest request) {
        BusTripModel existing = tripRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Trip not found: " + id));

        existing.setStartTime(request.startTime());
        existing.setEndTime(request.endTime());
        existing.setTripStatus(request.tripStatus());

        existing.setBus(busRepository.findById(request.busId())
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found: " + request.busId())));
        existing.setRoute(routeRepository.findById(request.routeId())
                .orElseThrow(() -> new ResourceNotFoundException("Route not found: " + request.routeId())));
        existing.setDriver(request.driverId() != null ?
                driverRepository.findById(request.driverId())
                        .orElseThrow(() -> new ResourceNotFoundException("Driver not found: " + request.driverId())) : null);
        existing.setConductor(request.conductorId() != null ?
                conductorRepository.findById(request.conductorId())
                        .orElseThrow(() -> new ResourceNotFoundException("Conductor not found: " + request.conductorId())) : null);

        BusTripModel updated = tripRepository.save(existing);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!tripRepository.existsById(id)) {
            throw new ResourceNotFoundException("Trip not found: " + id);
        }
        tripRepository.deleteById(id);
    }
}