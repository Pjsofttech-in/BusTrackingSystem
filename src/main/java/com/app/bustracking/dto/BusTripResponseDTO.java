package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class BusTripResponseDTO {
    private Long id;
    private BusResponseDTO bus;
    private BusRouteResponseDTO route;
    private DriverResponseDTO driver;
    private ConductorResponseDTO conductor;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String tripStatus;
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusResponseDTO getBus() {
        return bus;
    }

    public void setBus(BusResponseDTO bus) {
        this.bus = bus;
    }

    public BusRouteResponseDTO getRoute() {
        return route;
    }

    public void setRoute(BusRouteResponseDTO route) {
        this.route = route;
    }

    public DriverResponseDTO getDriver() {
        return driver;
    }

    public void setDriver(DriverResponseDTO driver) {
        this.driver = driver;
    }

    public ConductorResponseDTO getConductor() {
        return conductor;
    }

    public void setConductor(ConductorResponseDTO conductor) {
        this.conductor = conductor;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getTripStatus() {
        return tripStatus;
    }

    public void setTripStatus(String tripStatus) {
        this.tripStatus = tripStatus;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}