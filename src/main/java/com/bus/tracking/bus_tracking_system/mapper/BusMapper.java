package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.BusRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusResponseDTO;
import com.bus.tracking.bus_tracking_system.model.Bus;
import com.bus.tracking.bus_tracking_system.model.ServiceProvider;

public class BusMapper {

    // Convert RequestDTO → Entity (with ServiceProvider)
    public static Bus toEntity(BusRequestDTO dto, ServiceProvider serviceProvider) {
        Bus bus = new Bus();
        bus.setBusNumber(dto.getBusNumber());
        bus.setBusType(dto.getBusType());
        bus.setMfgYear(dto.getMfgYear());
        bus.setCapacity(dto.getCapacity());
        bus.setStatus(dto.getStatus());
        bus.setServiceProvider(serviceProvider);
        return bus;
    }

    // Convert Entity → ResponseDTO
    public static BusResponseDTO toDTO(Bus bus) {
        BusResponseDTO dto = new BusResponseDTO();
        dto.setId(bus.getId());
        dto.setBusNumber(bus.getBusNumber());
        dto.setBusType(bus.getBusType());
        dto.setMfgYear(bus.getMfgYear());
        dto.setCapacity(bus.getCapacity());
        dto.setStatus(bus.getStatus());
        dto.setCreatedAt(bus.getCreatedAt());
        dto.setUpdatedAt(bus.getUpdatedAt());

        // Include Service Provider data
        if (bus.getServiceProvider() != null) {
            dto.setServiceProviderId(bus.getServiceProvider().getId());
            dto.setServiceProviderName(bus.getServiceProvider().getName());
            dto.setServiceProviderBusNumber(bus.getServiceProvider().getBusNumber());
        }

        return dto;
    }
}