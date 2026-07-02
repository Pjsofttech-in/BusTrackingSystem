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

        if(dto.getLatitude() < -90 ||
                dto.getLatitude() > 90) {

            throw new RuntimeException("Invalid latitude");
        }

        if(dto.getLongitude() < -180 ||
                dto.getLongitude() > 180) {

            throw new RuntimeException("Invalid longitude");
        }
        BusLocation location =
                BusLocationMapper.toEntity(dto, bus);

        BusLocation saved = repo.save(location);

        return BusLocationMapper.toDTO(saved);
    }

    public BusLocationResponseDTO getLocationById(Long id) {

        BusLocation location = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Location not found"));

        return BusLocationMapper.toDTO(location);
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

    public void deleteLocation(Long id) {
        repo.deleteById(id);
    }
}