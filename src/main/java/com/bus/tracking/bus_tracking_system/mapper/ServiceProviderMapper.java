package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.ServiceProviderRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.ServiceProviderResponseDTO;
import com.bus.tracking.bus_tracking_system.model.ServiceProvider;

public class ServiceProviderMapper {

    // DTO → Entity
    public static ServiceProvider toEntity(ServiceProviderRequestDTO dto) {
        ServiceProvider provider = new ServiceProvider();
        provider.setName(dto.getName());
        provider.setMobile(dto.getMobile());
        provider.setEmail(dto.getEmail());
        provider.setBusNumber(dto.getBusNumber());
        provider.setState(dto.getState());
        provider.setCity(dto.getCity());
        provider.setPincode(dto.getPincode());
        return provider;
    }

    // Entity → DTO
    public static ServiceProviderResponseDTO toDTO(ServiceProvider provider) {
        if (provider == null) return null;

        ServiceProviderResponseDTO dto = new ServiceProviderResponseDTO();
        dto.setId(provider.getId());
        dto.setName(provider.getName());
        dto.setMobile(provider.getMobile());
        dto.setEmail(provider.getEmail());
        dto.setBusNumber(provider.getBusNumber());
        dto.setState(provider.getState());
        dto.setCity(provider.getCity());
        dto.setPincode(provider.getPincode());

        return dto;
    }
}