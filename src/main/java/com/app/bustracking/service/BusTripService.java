package com.app.bustracking.service;

import com.app.bustracking.dto.BusTripRequestDTO;
import com.app.bustracking.dto.BusTripResponseDTO;
import com.app.bustracking.mapper.BusTripMapper;
import com.app.bustracking.model.*;
import com.app.bustracking.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusTripService {

    private final BusTripRepository tripRepository;
    private final BusRepository busRepository;
    private final BusRouteRepository routeRepository;
    private final DriverRepository driverRepository;
    private final ConductorRepository conductorRepository;

    public BusTripService(BusTripRepository tripRepository,
                          BusRepository busRepository,
                          BusRouteRepository routeRepository,
                          DriverRepository driverRepository,
                          ConductorRepository conductorRepository) {
        this.tripRepository = tripRepository;
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
        this.driverRepository = driverRepository;
        this.conductorRepository = conductorRepository;
    }

    @Transactional
    public BusTripResponseDTO create(BusTripRequestDTO dto) {
        BusModel bus = busRepository.findById(dto.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        BusRouteModel route = routeRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));
        DriverModel driver = dto.getDriverId() != null ? driverRepository.findById(dto.getDriverId()).orElse(null) : null;
        ConductorModel conductor = dto.getConductorId() != null ? conductorRepository.findById(dto.getConductorId()).orElse(null) : null;

        BusTripModel trip = BusTripMapper.toEntity(dto, bus, route, driver, conductor);
        BusTripModel saved = tripRepository.save(trip);
        return BusTripMapper.toDTO(saved);
    }

    public List<BusTripResponseDTO> getAll() {
        return tripRepository.findAll().stream()
                .map(BusTripMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BusTripResponseDTO getById(Long id) {
        return tripRepository.findById(id)
                .map(BusTripMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public BusTripResponseDTO update(Long id, BusTripRequestDTO dto) {
        BusTripModel trip = tripRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trip not found"));
        BusModel bus = busRepository.findById(dto.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        BusRouteModel route = routeRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));
        DriverModel driver = dto.getDriverId() != null ? driverRepository.findById(dto.getDriverId()).orElse(null) : null;
        ConductorModel conductor = dto.getConductorId() != null ? conductorRepository.findById(dto.getConductorId()).orElse(null) : null;

        trip.setBus(bus);
        trip.setRoute(route);
        trip.setDriver(driver);
        trip.setConductor(conductor);
        trip.setStartTime(dto.getStartTime());
        trip.setEndTime(dto.getEndTime());
        trip.setTripStatus(dto.getTripStatus());

        BusTripModel updated = tripRepository.save(trip);
        return BusTripMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        tripRepository.deleteById(id);
    }
}