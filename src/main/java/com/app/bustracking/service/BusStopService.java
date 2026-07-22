package com.app.bustracking.service;

import com.app.bustracking.dto.BusStopRequestDTO;
import com.app.bustracking.dto.BusStopResponseDTO;
import com.app.bustracking.mapper.BusStopMapper;
import com.app.bustracking.model.BusModel;
import com.app.bustracking.model.BusStopModel;
import com.app.bustracking.repository.BusRepository;
import com.app.bustracking.repository.BusStopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusStopService {

    private final BusStopRepository busStopRepository;
    private final BusRepository busRepository;

    public BusStopService(BusStopRepository busStopRepository, BusRepository busRepository) {
        this.busStopRepository = busStopRepository;
        this.busRepository = busRepository;
    }

    @Transactional
    public BusStopResponseDTO create(BusStopRequestDTO dto) {
        BusModel bus = null;
        if (dto.getBusId() != null) {
            bus = busRepository.findById(dto.getBusId())
                    .orElseThrow(() -> new RuntimeException("Bus not found"));
        }
        BusStopModel entity = BusStopMapper.toEntity(dto, bus);
        BusStopModel saved = busStopRepository.save(entity);
        return BusStopMapper.toDTO(saved);
    }

    public List<BusStopResponseDTO> getAll() {
        return busStopRepository.findAll().stream()
                .map(BusStopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BusStopResponseDTO getById(Long id) {
        return busStopRepository.findById(id)
                .map(BusStopMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public BusStopResponseDTO update(Long id, BusStopRequestDTO dto) {
        BusStopModel stop = busStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BusStop not found"));
        BusModel bus = null;
        if (dto.getBusId() != null) {
            bus = busRepository.findById(dto.getBusId())
                    .orElseThrow(() -> new RuntimeException("Bus not found"));
        }
        stop.setBus(bus);
        stop.setStopName(dto.getStopName());
        stop.setLatitude(dto.getLatitude());
        stop.setLongitude(dto.getLongitude());
        if (dto.getReached() != null) {
            stop.setReached(dto.getReached());
            if (dto.getReached()) {
                stop.setReachedAt(LocalDateTime.now());
            }
        }
        BusStopModel updated = busStopRepository.save(stop);
        return BusStopMapper.toDTO(updated);
    }

    @Transactional
    public BusStopResponseDTO markReached(Long id) {
        BusStopModel stop = busStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BusStop not found"));
        stop.setReached(true);
        stop.setReachedAt(LocalDateTime.now());
        BusStopModel updated = busStopRepository.save(stop);
        return BusStopMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        busStopRepository.deleteById(id);
    }

    public BusStopModel findEntityById(Long id) {
        return busStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("BusStop not found"));
    }
}