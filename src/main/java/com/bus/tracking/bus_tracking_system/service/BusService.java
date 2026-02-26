package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.BusRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusResponseDTO;
import com.bus.tracking.bus_tracking_system.mapper.BusMapper;
import com.bus.tracking.bus_tracking_system.model.Bus;
import com.bus.tracking.bus_tracking_system.model.Route;
import com.bus.tracking.bus_tracking_system.model.Driver;
import com.bus.tracking.bus_tracking_system.model.Conductor;
import com.bus.tracking.bus_tracking_system.repository.*;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusService {

    private final BusRepository busRepository;
    private final RouteRepository routeRepository;
    private final DriverRepository driverRepository;
    private final ConductorRepository conductorRepository;

    public BusService(BusRepository busRepository,
                      RouteRepository routeRepository,
                      DriverRepository driverRepository,
                      ConductorRepository conductorRepository) {
        this.busRepository = busRepository;
        this.routeRepository = routeRepository;
        this.driverRepository = driverRepository;
        this.conductorRepository = conductorRepository;
    }

    // CREATE
    public BusResponseDTO addBus(BusRequestDTO dto) {

        Route route = routeRepository.findById(dto.getRouteId()).orElse(null);
        Driver driver = driverRepository.findById(dto.getDriverId()).orElse(null);
        Conductor conductor = conductorRepository.findById(dto.getConductorId()).orElse(null);

        Bus bus = BusMapper.toEntity(dto, route, driver, conductor);
        Bus saved = busRepository.save(bus);

        return BusMapper.toDTO(saved);
    }

    // GET ALL
    public List<BusResponseDTO> getAllBuses() {
        return busRepository.findAll()
                .stream()
                .map(BusMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public BusResponseDTO getBusById(Long id) {
        return busRepository.findById(id)
                .map(BusMapper::toDTO)
                .orElse(null);
    }

    // DAILY RUNNING
    public List<BusResponseDTO> getRunningBuses() {
        return busRepository.findByStatus("RUNNING")
                .stream()
                .map(BusMapper::toDTO)
                .collect(Collectors.toList());
    }

    // DELETE
    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }
}