package com.app.bustracking.service;

import com.app.bustracking.dto.BusRequestDTO;
import com.app.bustracking.dto.BusResponseDTO;
import com.app.bustracking.mapper.BusMapper;
import com.app.bustracking.model.BusModel;
import com.app.bustracking.model.ServiceProviderModel;
import com.app.bustracking.repository.BusRepository;
import com.app.bustracking.repository.ServiceProviderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusService {

    private final BusRepository busRepository;
    private final ServiceProviderRepository serviceProviderRepository;

    public BusService(BusRepository busRepository, ServiceProviderRepository serviceProviderRepository) {
        this.busRepository = busRepository;
        this.serviceProviderRepository = serviceProviderRepository;
    }

    @Transactional
    public BusResponseDTO create(BusRequestDTO dto) {
        ServiceProviderModel serviceProvider = null;
        if (dto.getServiceProviderId() != null) {
            serviceProvider = serviceProviderRepository.findById(dto.getServiceProviderId())
                    .orElseThrow(() -> new RuntimeException("ServiceProvider not found"));
        }
        BusModel bus = BusMapper.toEntity(dto, serviceProvider);
        BusModel saved = busRepository.save(bus);
        return BusMapper.toDTO(saved);
    }

    public List<BusResponseDTO> getAll() {
        return busRepository.findAll().stream()
                .map(BusMapper::toDTO)
                .collect(Collectors.toList());
    }

    public BusResponseDTO getById(Long id) {
        return busRepository.findById(id)
                .map(BusMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public BusResponseDTO update(Long id, BusRequestDTO dto) {
        BusModel bus = busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
        ServiceProviderModel serviceProvider = null;
        if (dto.getServiceProviderId() != null) {
            serviceProvider = serviceProviderRepository.findById(dto.getServiceProviderId())
                    .orElseThrow(() -> new RuntimeException("ServiceProvider not found"));
        }
        bus.setBusNumber(dto.getBusNumber());
        bus.setBusType(dto.getBusType());
        bus.setBusModelName(dto.getBusModelName());
        bus.setMfgYear(dto.getMfgYear());
        bus.setCapacity(dto.getCapacity());
        bus.setStatus(dto.getStatus());
        bus.setServiceProvider(serviceProvider);
        BusModel updated = busRepository.save(bus);
        return BusMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        busRepository.deleteById(id);
    }

    public BusModel findEntityById(Long id) {
        return busRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bus not found"));
    }
}