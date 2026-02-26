package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.ServiceProviderRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.ServiceProviderResponseDTO;
import com.bus.tracking.bus_tracking_system.mapper.ServiceProviderMapper;
import com.bus.tracking.bus_tracking_system.model.ServiceProvider;
import com.bus.tracking.bus_tracking_system.repository.ServiceProviderRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceProviderService {

    private final ServiceProviderRepository repo;

    public ServiceProviderService(ServiceProviderRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public ServiceProviderResponseDTO addServiceProvider(
            ServiceProviderRequestDTO dto) {

        ServiceProvider provider =
                ServiceProviderMapper.toEntity(dto);

        ServiceProvider saved = repo.save(provider);

        return ServiceProviderMapper.toDTO(saved);
    }

    // GET ALL
    public List<ServiceProviderResponseDTO> getAllServiceProviders() {

        return repo.findAll()
                .stream()
                .map(ServiceProviderMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public ServiceProviderResponseDTO getServiceProviderById(Long id) {

        ServiceProvider provider = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        return ServiceProviderMapper.toDTO(provider);
    }

    // UPDATE
    public ServiceProviderResponseDTO updateProvider(
            Long id,
            ServiceProviderRequestDTO dto) {

        ServiceProvider existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Provider not found"));

        existing.setName(dto.getName());
        existing.setMobile(dto.getMobile());
        existing.setEmail(dto.getEmail());
        existing.setAddress(dto.getAddress());

        ServiceProvider updated = repo.save(existing);

        return ServiceProviderMapper.toDTO(updated);
    }

    public void deleteProvider(Long id) {
        repo.deleteById(id);
    }
}