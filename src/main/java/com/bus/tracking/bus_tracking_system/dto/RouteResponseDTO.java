package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.List;

@Getter
@Setter
public class RouteResponseDTO {

    private Long id;
    private String routeName;

    private String startTime;
    private String stopTime;

    private String startPoint;
    private String endPoint;
    private double totalDistanceKm;
    private int estimatedTimeMin;
    private String status;

    //  Stops list response
    private List<RouteStopDTO> stops;
}