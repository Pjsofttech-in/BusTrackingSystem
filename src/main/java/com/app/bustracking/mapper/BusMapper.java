package com.app.bustracking.mapper;

import com.app.bustracking.dto.BusRequestDTO;
import com.app.bustracking.dto.BusResponseDTO;
import com.app.bustracking.model.BusModel;
import com.app.bustracking.model.ServiceProviderModel;

public class BusMapper {

    public static BusModel toEntity(BusRequestDTO dto, ServiceProviderModel serviceProvider) {
        BusModel bus = new BusModel();
        bus.setBusNumber(dto.getBusNumber());
        bus.setBusType(dto.getBusType());
        bus.setBusModelName(dto.getBusModelName());
        bus.setMfgYear(dto.getMfgYear());
        bus.setCapacity(dto.getCapacity());
        bus.setStatus(dto.getStatus());
        bus.setServiceProvider(serviceProvider);
        return bus;
    }

    public static BusResponseDTO toDTO(BusModel bus) {
        BusResponseDTO dto = new BusResponseDTO();
        dto.setId(bus.getId());
        dto.setBusNumber(bus.getBusNumber());
        dto.setBusType(bus.getBusType());
        dto.setBusModelName(bus.getBusModelName());
        dto.setMfgYear(bus.getMfgYear());
        dto.setCapacity(bus.getCapacity());
        dto.setStatus(bus.getStatus());
        dto.setCreatedAt(bus.getCreatedAt());
        dto.setUpdatedAt(bus.getUpdatedAt());

        // ✅ Set service provider details
        if (bus.getServiceProvider() != null) {
            dto.setServiceProviderId(bus.getServiceProvider().getId());
            dto.setServiceProviderName(bus.getServiceProvider().getServiceprovidername());
        } else {
            dto.setServiceProviderId(null);
            dto.setServiceProviderName(null);
        }

        return dto;
    }
}