// src/main/java/com/app/bustracking/service/impl/BusStopServiceImpl.java
package com.app.bustracking.service.impl;

import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.Mapper.BusStopMapper;
import com.app.bustracking.Model.BusStopModel;
import com.app.bustracking.repository.BusStopRepository;
import com.app.bustracking.Request.BusStopRequest;
import com.app.bustracking.Response.BusStopResponse;
import com.app.bustracking.service.BusStopService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusStopServiceImpl implements BusStopService {

    private final BusStopRepository busStopRepository;
    private final BusStopMapper mapper;

    @Override
    public List<BusStopResponse> getAll() {
        return busStopRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BusStopResponse getById(Long id) {
        BusStopModel stop = busStopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus stop not found with id: " + id));
        return mapper.toResponse(stop);
    }

    @Override
    @Transactional
    public BusStopResponse create(BusStopRequest request) {
        BusStopModel stop = mapper.toModel(request);
        BusStopModel saved = busStopRepository.save(stop);
        return mapper.toResponse(saved);
    }

    @Override
    @Transactional
    public BusStopResponse update(Long id, BusStopRequest request) {
        BusStopModel existing = busStopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus stop not found with id: " + id));

        existing.setStopName(request.stopName());
        existing.setLatitude(request.latitude());
        existing.setLongitude(request.longitude());

        BusStopModel updated = busStopRepository.save(existing);
        return mapper.toResponse(updated);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!busStopRepository.existsById(id)) {
            throw new ResourceNotFoundException("Bus stop not found with id: " + id);
        }
        busStopRepository.deleteById(id);
    }

    @Override
    @Transactional
    public BusStopResponse markReached(Long id) {
        BusStopModel stop = busStopRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Bus stop not found with id: " + id));
        stop.setReached(true);
        stop.setReachedAt(LocalDateTime.now());
        BusStopModel updated = busStopRepository.save(stop);
        return mapper.toResponse(updated);
    }
}