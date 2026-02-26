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
        provider.setAddress(dto.getAddress());

        return provider;
    }

    // Entity → DTO
    public static ServiceProviderResponseDTO toDTO(ServiceProvider provider) {

        ServiceProviderResponseDTO dto = new ServiceProviderResponseDTO();

        dto.setId(provider.getId());
        dto.setName(provider.getName());
        dto.setMobile(provider.getMobile());
        dto.setEmail(provider.getEmail());
        dto.setAddress(provider.getAddress());

        return dto;
    }
}