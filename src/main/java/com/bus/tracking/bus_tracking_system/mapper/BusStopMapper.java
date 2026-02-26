package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.*;
import com.bus.tracking.bus_tracking_system.model.*;

public class BusStopMapper {

    public static BusStop toEntity(BusStopRequestDTO dto, Bus bus) {

        BusStop stop = new BusStop();

        stop.setBus(bus);
        stop.setStopName(dto.getStopName());
        stop.setLatitude(dto.getLatitude());
        stop.setLongitude(dto.getLongitude());
        stop.setSequenceNumber(dto.getSequenceNumber());

        return stop;
    }

    public static BusStopResponseDTO toDTO(BusStop stop) {

        BusStopResponseDTO dto = new BusStopResponseDTO();

        dto.setId(stop.getId());
        dto.setStopName(stop.getStopName());
        dto.setLatitude(stop.getLatitude());
        dto.setLongitude(stop.getLongitude());
        dto.setSequenceNumber(stop.getSequenceNumber());
        dto.setReached(stop.isReached());
        dto.setReachedAt(stop.getReachedAt());

        return dto;
    }
}