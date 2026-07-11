package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.BusTripRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.BusTripResponseDTO;
import com.bus.tracking.bus_tracking_system.model.BusTrip;

public class BusTripMapper {

    // DTO → ENTITY
    public static BusTrip toEntity(BusTripRequestDTO dto) {

        if (dto == null) return null;

        BusTrip trip = new BusTrip();
        trip.setStartTime(dto.getStartTime());
        trip.setEndTime(dto.getEndTime());

        trip.setTripStatus(dto.getTripStatus());

        return trip;
    }

    // ENTITY → DTO
    public static BusTripResponseDTO toDTO(BusTrip trip) {

        if (trip == null) return null;

        BusTripResponseDTO dto = new BusTripResponseDTO();

        dto.setId(trip.getId());

        //  BUS
        if (trip.getBus() != null) {
            dto.setBusId(trip.getBus().getId());
//            dto.setBusNumber(trip.getBus().getBusNumber());
        }

        //  ROUTE
        if (trip.getRoute() != null) {
            dto.setRouteId(trip.getRoute().getId());
            dto.setRouteName(trip.getRoute().getRouteName());
        }

        // DRIVER
        if (trip.getDriver() != null) {
            dto.setDriverId(trip.getDriver().getId());
            dto.setDriverName(trip.getDriver().getName());
        }

        //  CONDUCTOR
        if (trip.getConductor() != null) {
            dto.setConductorId(trip.getConductor().getId());
            dto.setConductorName(trip.getConductor().getName());
        }

        dto.setStartTime(trip.getStartTime());
        dto.setEndTime(trip.getEndTime());
        dto.setTripStatus(trip.getTripStatus());
        dto.setCreatedAt(trip.getCreatedAt());

        return dto;
    }
}