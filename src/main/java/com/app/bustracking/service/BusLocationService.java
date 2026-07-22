package com.app.bustracking.service;

import com.app.bustracking.dto.BusLocationRequestDTO;
import com.app.bustracking.dto.BusLocationResponseDTO;
import com.app.bustracking.mapper.BusLocationMapper;
import com.app.bustracking.model.BusLocationModel;
import com.app.bustracking.model.BusModel;
import com.app.bustracking.model.DirectionModel;
import com.app.bustracking.repository.BusLocationRepository;
import com.app.bustracking.repository.BusRepository;
import com.app.bustracking.repository.DirectionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusLocationService {

    private final BusLocationRepository locationRepository;
    private final BusRepository busRepository;
    private final DirectionRepository directionRepository;

    public BusLocationService(BusLocationRepository locationRepository,
                              BusRepository busRepository,
                              DirectionRepository directionRepository) {
        this.locationRepository = locationRepository;
        this.busRepository = busRepository;
        this.directionRepository = directionRepository;
    }

    @Transactional
    public BusLocationResponseDTO saveLocation(BusLocationRequestDTO dto) {
        BusModel bus = busRepository.findById(dto.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        DirectionModel direction = null;
        if (dto.getDirectionId() != null) {
            direction = directionRepository.findById(dto.getDirectionId())
                    .orElseThrow(() -> new RuntimeException("Direction not found"));
        }
        BusLocationModel entity = BusLocationMapper.toEntity(dto, bus, direction);
        if (entity.getTimestamp() == null) {
            entity.setTimestamp(LocalDateTime.now());
        }
        BusLocationModel saved = locationRepository.save(entity);
        return BusLocationMapper.toDTO(saved);
    }

    public List<BusLocationResponseDTO> getAll() {
        return locationRepository.findAll().stream()
                .map(BusLocationMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BusLocationResponseDTO getById(Long id) {
        return locationRepository.findById(id)
                .map(BusLocationMapper::toDTO)
                .orElse(null);
    }

    public BusLocationResponseDTO getLatestByBusId(Long busId) {
        return locationRepository.findTopByBusIdOrderByTimestampDesc(busId)
                .map(BusLocationMapper::toDTO)
                .orElse(null);
    }

    public List<BusLocationResponseDTO> getHistoryByBusId(Long busId, int limit) {
        return locationRepository.findByBusIdOrderByTimestampDesc(busId).stream()
                .limit(limit)
                .map(BusLocationMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        locationRepository.deleteById(id);
    }
}