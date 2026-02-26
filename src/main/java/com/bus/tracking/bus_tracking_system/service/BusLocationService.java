package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.BusLocationRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusLocationResponseDTO;
import com.bus.tracking.bus_tracking_system.mapper.BusLocationMapper;
import com.bus.tracking.bus_tracking_system.model.Bus;
import com.bus.tracking.bus_tracking_system.model.BusLocation;
import com.bus.tracking.bus_tracking_system.repository.BusLocationRepository;
import com.bus.tracking.bus_tracking_system.repository.BusRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusLocationService {

    private final BusLocationRepository repo;
    private final BusRepository busRepository;

    public BusLocationService(BusLocationRepository repo,
                              BusRepository busRepository) {
        this.repo = repo;
        this.busRepository = busRepository;
    }

    public BusLocationResponseDTO updateLocation(
            BusLocationRequestDTO dto) {

        Bus bus = busRepository.findById(dto.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        BusLocation location =
                BusLocationMapper.toEntity(dto, bus);

        BusLocation saved = repo.save(location);

        return BusLocationMapper.toDTO(saved);
    }

    public BusLocationResponseDTO getLatestLocation(Long busId) {

        BusLocation location =
                repo.findTopByBus_IdOrderByRecordedAtDesc(busId);

        if (location == null) return null;

        return BusLocationMapper.toDTO(location);
    }

    public List<BusLocationResponseDTO> getLocationHistory(Long busId) {

        return repo.findByBus_IdOrderByRecordedAtAsc(busId)
                .stream()
                .map(BusLocationMapper::toDTO)
                .collect(Collectors.toList());
    }
}