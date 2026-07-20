package com.app.bustracking.service.impl;

import com.app.bustracking.Mapper.BusMapper;
import com.app.bustracking.Model.BusModel;
import com.app.bustracking.Model.ServiceProviderModel;
import com.app.bustracking.repository.BusRepository;
import com.app.bustracking.repository.ServiceProviderRepository;
import com.app.bustracking.Request.BusRequest;
import com.app.bustracking.Response.BusResponse;
import com.app.bustracking.service.BusService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BusServiceImpl implements BusService {

    private final BusRepository busRepository;
    private final ServiceProviderRepository providerRepository;
    private final BusMapper mapper;

    @Override
    public List<BusResponse> getAll() {
        return busRepository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public BusResponse getById(Long id) {
        BusModel bus = busRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus not found"));
        return mapper.toResponse(bus);
    }

    @Override
    @Transactional
    public BusResponse create(BusRequest request) {
        ServiceProviderModel provider = null;
        if (request.serviceProviderId() != null) {
            provider = providerRepository.findById(request.serviceProviderId())
                    .orElseThrow(() -> new EntityNotFoundException("Provider not found"));
        }
        BusModel bus = mapper.toModel(request);
        bus.setServiceProvider(provider);
        return mapper.toResponse(busRepository.save(bus));
    }

    @Override
    @Transactional
    public BusResponse update(Long id, BusRequest request) {
        BusModel existing = busRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Bus not found"));

        // Update fields
        existing.setBusNumber(request.busNumber());
        existing.setBusType(request.busType());
        existing.setBusModelName(request.busModelName());
        existing.setMfgYear(request.mfgYear());
        existing.setCapacity(request.capacity());
        existing.setStatus(request.status());

        // Update provider relation
        if (request.serviceProviderId() != null) {
            ServiceProviderModel provider = providerRepository.findById(request.serviceProviderId())
                    .orElseThrow(() -> new EntityNotFoundException("Provider not found"));
            existing.setServiceProvider(provider);
        } else {
            existing.setServiceProvider(null);
        }

        return mapper.toResponse(busRepository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!busRepository.existsById(id)) {
            throw new EntityNotFoundException("Bus not found");
        }
        busRepository.deleteById(id);
    }
}