package com.app.bustracking.service.impl;

import com.app.bustracking.Mapper.BusLocationMapper;
import com.app.bustracking.Model.BusLocationModel;
import com.app.bustracking.Model.BusModel;
import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.repository.BusLocationRepository;
import com.app.bustracking.repository.BusRepository;
import com.app.bustracking.repository.DirectionRepository;
import com.app.bustracking.Request.BusLocationRequest;
import com.app.bustracking.Response.BusLocationResponse;
import com.app.bustracking.service.BusLocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusLocationServiceImpl implements BusLocationService {

    private final BusLocationRepository locationRepository;
    private final BusRepository busRepository;
    private final DirectionRepository directionRepository;
    private final BusLocationMapper mapper;

    @Override
    @Transactional
    public BusLocationResponse saveLocation(BusLocationRequest request) {
        // Map request to entity – pass repositories as context
        BusLocationModel location = mapper.toModel(request, busRepository, directionRepository);
        // The afterToMapping will set bus and direction based on IDs

        BusLocationModel saved = locationRepository.save(location);
        return mapper.toResponse(saved);
    }

    @Override
    public BusLocationResponse getLatestByBusId(Long busId) {
        BusModel bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id: " + busId));
        BusLocationModel latest = locationRepository.findTopByBusOrderByTimestampDesc(bus)
                .orElseThrow(() -> new ResourceNotFoundException("No location found for bus: " + busId));
        return mapper.toResponse(latest);
    }

    @Override
    public List<BusLocationResponse> getHistoryByBusId(Long busId, int limit) {
        BusModel bus = busRepository.findById(busId)
                .orElseThrow(() -> new ResourceNotFoundException("Bus not found with id: " + busId));
        return locationRepository.findByBusOrderByTimestampDesc(bus)
                .stream()
                .limit(limit)
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<BusLocationResponse> getAll() {
        return locationRepository.findAll()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }
}