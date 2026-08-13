package com.app.bustracking.service;

import com.app.bustracking.dto.ServiceProviderRequestDTO;
import com.app.bustracking.dto.ServiceProviderResponseDTO;
import com.app.bustracking.mapper.ServiceProviderMapper;
import com.app.bustracking.model.ServiceProviderModel;
import com.app.bustracking.repository.ServiceProviderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServiceProviderService {

    private final ServiceProviderRepository repository;

    public ServiceProviderService(ServiceProviderRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public ServiceProviderResponseDTO create(ServiceProviderRequestDTO dto) {
        ServiceProviderModel entity = ServiceProviderMapper.toEntity(dto);
        ServiceProviderModel saved = repository.save(entity);
        return ServiceProviderMapper.toDTO(saved);
    }

    public List<ServiceProviderResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(ServiceProviderMapper::toDTO)
                .collect(Collectors.toList());
    }

    public ServiceProviderResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(ServiceProviderMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public ServiceProviderResponseDTO update(Long id, ServiceProviderRequestDTO dto) {
        ServiceProviderModel entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServiceProvider not found"));
        entity.setServiceprovidername(dto.getServiceprovidername());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        entity.setRegistrationNumber(dto.getRegistrationNumber());
        entity.setAddress(dto.getAddress());
        entity.setStatus(dto.getStatus() != null ? dto.getStatus() : entity.getStatus());
        ServiceProviderModel updated = repository.save(entity);
        return ServiceProviderMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public ServiceProviderModel findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("ServiceProvider not found"));
    }
}