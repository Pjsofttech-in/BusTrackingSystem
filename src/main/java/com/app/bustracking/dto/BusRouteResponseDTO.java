package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
public class BusRouteResponseDTO {
    private Long id;
    private String routeName;
    private String description;

    // Start/End stop details
    private Long startStopId;
    private String startStopName;
    private Long endStopId;
    private String endStopName;

    private LocalTime startTime;
    private LocalTime endTime;
    private Double totalDistanceKm;
    private Integer estimatedTimeMin;
    private String status;

    // Bus, Driver, Conductor details
    private Long busId;
    private String busNumber;
    private Long driverId;
    private String driverName;
    private Long conductorId;
    private String conductorName;

    private List<BusRouteStopResponseDTO> stops;
    private LocalDateTime createdAt;
}