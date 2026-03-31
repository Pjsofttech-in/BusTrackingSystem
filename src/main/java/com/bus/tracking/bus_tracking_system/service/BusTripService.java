package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.BusTripRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusTripResponseDTO;
import com.bus.tracking.bus_tracking_system.mapper.BusTripMapper;
import com.bus.tracking.bus_tracking_system.model.*;
import com.bus.tracking.bus_tracking_system.repository.*;
import org.springframework.stereotype.Service;

@Service
public class BusTripService {

    private final BusTripRepository tripRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final DriverRepository driverRepository;

    public BusTripService(BusTripRepository tripRepository,
                          BusRepository busRepository,
                          RouteRepository routeRepository,
                          DriverRepository driverRepository) {
        this.tripRepository = tripRepository;
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
        this.driverRepository = driverRepository;
    }

    public BusTripResponseDTO createTrip(BusTripRequestDTO dto) {

        BusTrip trip = BusTripMapper.toEntity(dto);

        Bus bus = busRepository.findById(dto.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        Route route = routeRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        Driver driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        trip.setBus(bus);
        trip.setRoute(route);
        trip.setDriver(driver);

        return BusTripMapper.toDTO(tripRepository.save(trip));
    }
}