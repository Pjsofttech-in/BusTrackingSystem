package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.BusTripRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusTripResponseDTO;
import com.bus.tracking.bus_tracking_system.mapper.BusTripMapper;
import com.bus.tracking.bus_tracking_system.model.*;
import com.bus.tracking.bus_tracking_system.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BusTripService {

    private final BusTripRepository tripRepository;
    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final DriverRepository driverRepository;
    private final ConductorRepository conductorRepository; //  ADD

    public BusTripService(BusTripRepository tripRepository,
                          BusRepository busRepository,
                          RouteRepository routeRepository,
                          DriverRepository driverRepository,
                          ConductorRepository conductorRepository) { // ADD
        this.tripRepository = tripRepository;
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
        this.driverRepository = driverRepository;
        this.conductorRepository = conductorRepository; //  ADD
    }

    //  CREATE
    public BusTripResponseDTO createTrip(BusTripRequestDTO dto) {

        BusTrip trip = BusTripMapper.toEntity(dto);

        Bus bus = busRepository.findById(dto.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        Route route = routeRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        Driver driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        Conductor conductor = conductorRepository.findById(dto.getConductorId())
                .orElseThrow(() -> new RuntimeException("Conductor not found"));

        trip.setBus(bus);
        trip.setRoute(route);
        trip.setDriver(driver);
        trip.setConductor(conductor);

        return BusTripMapper.toDTO(tripRepository.save(trip));
    }

    //  GET ALL
    public List<BusTripResponseDTO> getAllTrips() {
        return tripRepository.findAll()
                .stream()
                .map(BusTripMapper::toDTO)
                .toList();
    }

    //  GET BY ID
    public BusTripResponseDTO getTripById(Long id) {
        BusTrip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        return BusTripMapper.toDTO(trip);
    }

    //  UPDATE ( MAIN FIX)
    public BusTripResponseDTO updateTrip(Long id, BusTripRequestDTO dto) {

        BusTrip trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));

        // Fetch related entities again
        Bus bus = busRepository.findById(dto.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        Route route = routeRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));

        Driver driver = driverRepository.findById(dto.getDriverId())
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        Conductor conductor = conductorRepository.findById(dto.getConductorId())
                .orElseThrow(() -> new RuntimeException("Conductor not found"));

        // Updatedfields
        trip.setBus(bus);
        trip.setRoute(route);
        trip.setDriver(driver);
        trip.setConductor(conductor);

        trip.setStartTime(dto.getStartTime());
        trip.setEndTime(dto.getEndTime());
        trip.setTripStatus(dto.getTripStatus());

        return BusTripMapper.toDTO(tripRepository.save(trip));
    }

    //  DELETE
    public void deleteTrip(Long id) {
        tripRepository.deleteById(id);
    }
}