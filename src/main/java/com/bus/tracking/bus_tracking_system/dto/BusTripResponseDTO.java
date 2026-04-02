package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BusTripResponseDTO {

    private Long id;

    private Long busId;
    private String busNumber;

    private Long routeId;
    private String routeName;

    private Long driverId;
    private String driverName;

    private Long conductorId;
    private String conductorName;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    private String tripStatus;
    private LocalDateTime createdAt;
}