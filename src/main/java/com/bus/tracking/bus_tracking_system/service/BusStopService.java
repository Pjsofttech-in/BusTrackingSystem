package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.BusStopRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusStopResponseDTO;
import com.bus.tracking.bus_tracking_system.mapper.BusStopMapper;
import com.bus.tracking.bus_tracking_system.model.Bus;
import com.bus.tracking.bus_tracking_system.model.BusStop;
import com.bus.tracking.bus_tracking_system.repository.BusRepository;
import com.bus.tracking.bus_tracking_system.repository.BusStopRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusStopService {

    private final BusStopRepository repo;
    private final BusRepository busRepository;

    public BusStopService(BusStopRepository repo,
                          BusRepository busRepository) {
        this.repo = repo;
        this.busRepository = busRepository;
    }

    // ADD STOP
    public BusStopResponseDTO addStop(BusStopRequestDTO dto) {

        Bus bus = busRepository.findById(dto.getBusId())
                .orElseThrow(() -> new RuntimeException("Bus not found"));

        BusStop stop = BusStopMapper.toEntity(dto, bus);

        BusStop saved = repo.save(stop);

        return BusStopMapper.toDTO(saved);
    }

    // GET STOPS BY BUS
    public List<BusStopResponseDTO> getStopsByBus(Long busId) {

        return repo.findByBus_IdOrderBySequenceNumberAsc(busId)
                .stream()
                .map(BusStopMapper::toDTO)
                .collect(Collectors.toList());
    }

    // MARK STOP REACHED
    public BusStopResponseDTO markStopReached(Long stopId) {

        BusStop stop = repo.findById(stopId)
                .orElseThrow(() -> new RuntimeException("Stop not found"));

        stop.setReached(true);
        stop.setReachedAt(LocalDateTime.now());

        BusStop updated = repo.save(stop);

        return BusStopMapper.toDTO(updated);
    }

    // COUNT REACHED
    public long countStopsReached(Long busId) {

        return repo.findByBus_IdOrderBySequenceNumberAsc(busId)
                .stream()
                .filter(BusStop::isReached)
                .count();
    }

    public void deleteStop(Long id) {
        repo.deleteById(id);
    }
}