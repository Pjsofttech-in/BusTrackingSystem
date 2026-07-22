package com.app.bustracking.service;

import com.app.bustracking.dto.BusRouteStopRequestDTO;
import com.app.bustracking.dto.BusRouteStopResponseDTO;
import com.app.bustracking.mapper.BusRouteStopMapper;
import com.app.bustracking.model.BusRouteModel;
import com.app.bustracking.model.BusRouteStopModel;
import com.app.bustracking.model.BusStopModel;
import com.app.bustracking.repository.BusRouteRepository;
import com.app.bustracking.repository.BusRouteStopRepository;
import com.app.bustracking.repository.BusStopRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusRouteStopService {

    private final BusRouteStopRepository routeStopRepository;
    private final BusRouteRepository routeRepository;
    private final BusStopRepository stopRepository;

    public BusRouteStopService(BusRouteStopRepository routeStopRepository,
                               BusRouteRepository routeRepository,
                               BusStopRepository stopRepository) {
        this.routeStopRepository = routeStopRepository;
        this.routeRepository = routeRepository;
        this.stopRepository = stopRepository;
    }

    @Transactional
    public BusRouteStopResponseDTO create(BusRouteStopRequestDTO dto) {
        BusRouteModel route = routeRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new RuntimeException("Route not found"));
        BusStopModel stop = stopRepository.findById(dto.getStopId())
                .orElseThrow(() -> new RuntimeException("Stop not found"));

        BusRouteStopModel entity = new BusRouteStopModel();
        entity.setRoute(route);
        entity.setStop(stop);
        entity.setSequence(dto.getSequence());

        BusRouteStopModel saved = routeStopRepository.save(entity);
        return BusRouteStopMapper.toDTO(saved);
    }

    public List<BusRouteStopResponseDTO> getAll() {
        return routeStopRepository.findAll().stream()
                .map(BusRouteStopMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BusRouteStopResponseDTO getById(Long id) {
        return routeStopRepository.findById(id)
                .map(BusRouteStopMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public BusRouteStopResponseDTO update(Long id, BusRouteStopRequestDTO dto) {
        BusRouteStopModel entity = routeStopRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("RouteStop not found"));

        // Update only if provided
        if (dto.getRouteId() != null) {
            BusRouteModel route = routeRepository.findById(dto.getRouteId())
                    .orElseThrow(() -> new RuntimeException("Route not found"));
            entity.setRoute(route);
        }
        if (dto.getStopId() != null) {
            BusStopModel stop = stopRepository.findById(dto.getStopId())
                    .orElseThrow(() -> new RuntimeException("Stop not found"));
            entity.setStop(stop);
        }
        if (dto.getSequence() != null) {
            entity.setSequence(dto.getSequence());
        }

        BusRouteStopModel updated = routeStopRepository.save(entity);
        return BusRouteStopMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        routeStopRepository.deleteById(id);
    }

    public List<BusRouteStopResponseDTO> getByRouteId(Long routeId) {
        return routeStopRepository.findByRouteIdOrderBySequenceAsc(routeId).stream()
                .map(BusRouteStopMapper::toDTO)
                .collect(Collectors.toList());
    }
}