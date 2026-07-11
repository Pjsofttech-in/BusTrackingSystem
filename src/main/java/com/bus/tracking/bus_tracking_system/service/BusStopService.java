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

        if (repo.existsByBus_IdAndStopName(
                dto.getBusId(),
                dto.getStopName())) {

            throw new RuntimeException(
                    "Stop already exists for this bus");
        }

        if (repo.existsByBus_IdAndSequenceNumber(
                dto.getBusId(),
                dto.getSequenceNumber())) {

            throw new RuntimeException(
                    "Sequence number already exists");
        }

        Bus bus = busRepository.findById(dto.getBusId())
                .orElseThrow(() ->
                        new RuntimeException("Bus not found"));

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

    //GET STOP BY ID
    public BusStopResponseDTO getStopById(Long id) {

        BusStop stop = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Stop not found"));

        return BusStopMapper.toDTO(stop);
    }

    //UPDATE
    public BusStopResponseDTO updateStop(
            Long id,
            BusStopRequestDTO dto) {

        BusStop stop = repo.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Stop not found"));

        stop.setStopName(dto.getStopName());
        stop.setLatitude(dto.getLatitude());
        stop.setLongitude(dto.getLongitude());
        stop.setSequenceNumber(dto.getSequenceNumber());

        BusStop updated = repo.save(stop);

        return BusStopMapper.toDTO(updated);
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
   // DELETE STOP
    public void deleteStop(Long id) {
        repo.deleteById(id);
    }
      // GET ALL STOPS
    public List<BusStopResponseDTO> getAllStops() {

        return repo.findAll()
                .stream()
                .map(BusStopMapper::toDTO)
                .collect(Collectors.toList());
    }
}