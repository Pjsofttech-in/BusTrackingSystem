package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusStopRequestDTO {

    private Long busId;
    private String stopName;
    private double latitude;
    private double longitude;
    private int sequenceNumber;

    // getters and setters

}