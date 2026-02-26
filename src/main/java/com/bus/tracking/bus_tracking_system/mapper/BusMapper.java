package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.BusRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusResponseDTO;
import com.bus.tracking.bus_tracking_system.model.Bus;
import com.bus.tracking.bus_tracking_system.model.Route;
import com.bus.tracking.bus_tracking_system.model.Driver;
import com.bus.tracking.bus_tracking_system.model.Conductor;

public class BusMapper {

    // Convert RequestDTO → Entity
    public static Bus toEntity(BusRequestDTO dto,
                               Route route,
                               Driver driver,
                               Conductor conductor) {

        Bus bus = new Bus();
        bus.setBusNumber(dto.getBusNumber());
        bus.setBusType(dto.getBusType());
        bus.setCapacity(dto.getCapacity());
        bus.setStatus(dto.getStatus());

        bus.setRoute(route);
        bus.setDriver(driver);
        bus.setConductor(conductor);

        return bus;
    }

    // Convert Entity → ResponseDTO
    public static BusResponseDTO toDTO(Bus bus) {

        BusResponseDTO dto = new BusResponseDTO();

        dto.setId(bus.getId());
        dto.setBusNumber(bus.getBusNumber());
        dto.setBusType(bus.getBusType());
        dto.setCapacity(bus.getCapacity());
        dto.setStatus(bus.getStatus());

        if (bus.getRoute() != null) {
            dto.setRouteId(bus.getRoute().getId());
            dto.setRouteName(bus.getRoute().getRouteName());
        }

        if (bus.getDriver() != null) {
            dto.setDriverId(bus.getDriver().getId());
            dto.setDriverName(bus.getDriver().getName());
        }

        if (bus.getConductor() != null) {
            dto.setConductorId(bus.getConductor().getId());
            dto.setConductorName(bus.getConductor().getName());
        }

        return dto;
    }
}