package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.BusRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusResponseDTO;
import com.bus.tracking.bus_tracking_system.mapper.BusMapper;
import com.bus.tracking.bus_tracking_system.model.Bus;
import com.bus.tracking.bus_tracking_system.model.ServiceProvider;
import com.bus.tracking.bus_tracking_system.repository.BusRepository;
import com.bus.tracking.bus_tracking_system.repository.ServiceProviderRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusService {

    private final BusRepository busRepository;
    private final ServiceProviderRepository serviceProviderRepository;

    public BusService(BusRepository busRepository,
                      ServiceProviderRepository serviceProviderRepository) {
        this.busRepository = busRepository;
        this.serviceProviderRepository = serviceProviderRepository;
    }

    // CREATE
    @Transactional
    public BusResponseDTO addBus(BusRequestDTO dto) {
        ServiceProvider serviceProvider = null;

        if (dto.getServiceProviderId() != null) {
            serviceProvider = serviceProviderRepository.findById(dto.getServiceProviderId())
                    .orElseThrow(() -> new RuntimeException("Service Provider not found"));
        }

        Bus bus = BusMapper.toEntity(dto, serviceProvider);
        Bus savedBus = busRepository.save(bus);
        return BusMapper.toDTO(savedBus);
    }

    // GET ALL with JOIN
    public List<BusResponseDTO> getAllBuses() {
        List<Bus> buses = busRepository.findAllWithServiceProvider();
        return buses.stream()
                .map(BusMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID with JOIN
    public BusResponseDTO getBusById(Long id) {
        Bus bus = busRepository.findByIdWithServiceProvider(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        return BusMapper.toDTO(bus);
    }

    // GET BUSES WHERE bus.busNumber = serviceProvider.busNumber (MATCHING)
    public List<BusResponseDTO> getBusesMatchingProviderBusNumber() {
        List<Bus> buses = busRepository.findBusesMatchingProviderBusNumber();
        return buses.stream()
                .map(BusMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET BUSES BY SERVICE PROVIDER BUS NUMBER (JOIN)
    public List<BusResponseDTO> getBusesByProviderBusNumber(String providerBusNumber) {
        List<Bus> buses = busRepository.findByServiceProviderBusNumber(providerBusNumber);
        return buses.stream()
                .map(BusMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET BUS BY BUS NUMBER
    public BusResponseDTO getBusByNumber(String busNumber) {
        Bus bus = busRepository.findByBusNumber(busNumber)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        return BusMapper.toDTO(bus);
    }

    // GET ACTIVE BUSES MATCHING PROVIDER BUS NUMBER
    public List<BusResponseDTO> getActiveBusesMatchingProviderBusNumber() {
        List<Bus> buses = busRepository.findActiveBusesMatchingProviderBusNumber();
        return buses.stream()
                .map(BusMapper::toDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    @Transactional
    public BusResponseDTO updateBus(Long id, BusRequestDTO dto) {
        Bus existingBus = busRepository.findByIdWithServiceProvider(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        ServiceProvider serviceProvider = null;
        if (dto.getServiceProviderId() != null) {
            serviceProvider = serviceProviderRepository.findById(dto.getServiceProviderId())
                    .orElseThrow(() -> new RuntimeException("Service Provider not found"));
        }

        existingBus.setBusNumber(dto.getBusNumber());
        existingBus.setBusType(dto.getBusType());
        existingBus.setMfgYear(dto.getMfgYear());
        existingBus.setCapacity(dto.getCapacity());
        existingBus.setStatus(dto.getStatus());
        existingBus.setServiceProvider(serviceProvider);

        Bus updatedBus = busRepository.save(existingBus);
        return BusMapper.toDTO(updatedBus);
    }

    // DELETE
    @Transactional
    public void deleteBus(Long id) {
        busRepository.deleteById(id);
    }

    // GET DAILY RUNNING BUSES
    public List<BusResponseDTO> getRunningBuses() {
        return busRepository.findByStatus("RUNNING")
                .stream()
                .map(BusMapper::toDTO)
                .collect(Collectors.toList());
    }
}