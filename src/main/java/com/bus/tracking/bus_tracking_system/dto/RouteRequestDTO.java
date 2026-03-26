package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RouteRequestDTO {

    private String routeName;
    private String startTime;
    private String stopTime;
    private String startPoint;
    private String endPoint;
    private double totalDistanceKm;
    private int estimatedTimeMin;
    private String status;
    private String busStop;
}