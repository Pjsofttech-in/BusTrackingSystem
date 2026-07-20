package com.app.bustracking.service.impl;

import com.app.bustracking.Mapper.ServiceProviderMapper;
import com.app.bustracking.Model.ServiceProviderModel;
import com.app.bustracking.repository.ServiceProviderRepository;
import com.app.bustracking.Request.ServiceProviderRequest;
import com.app.bustracking.Response.ServiceProviderResponse;
import com.app.bustracking.service.ServiceProviderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceProviderServiceImpl implements ServiceProviderService {

    private final ServiceProviderRepository repository;
    private final ServiceProviderMapper mapper;

    @Override
    public List<ServiceProviderResponse> getAll() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ServiceProviderResponse getById(Long id) {
        ServiceProviderModel model = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found with id: " + id));
        return mapper.toResponse(model);
    }

    @Override
    public ServiceProviderResponse create(ServiceProviderRequest request) {
        ServiceProviderModel model = mapper.toModel(request);
        return mapper.toResponse(repository.save(model));
    }

    @Override
    @Transactional
    public ServiceProviderResponse update(Long id, ServiceProviderRequest request) {
        ServiceProviderModel existing = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Provider not found with id: " + id));

        // Updated field names
        existing.setServiceprovidername(request.serviceprovidername());
        existing.setEmail(request.email());
        existing.setMobile(request.mobile());
        existing.setCity(request.city());
        existing.setState(request.state());
        existing.setPincode(request.pincode());

        return mapper.toResponse(repository.save(existing));
    }

    @Override
    @Transactional
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Provider not found with id: " + id);
        }
        repository.deleteById(id);
    }
}