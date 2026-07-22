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
    private BusStopResponseDTO startStop;
    private BusStopResponseDTO endStop;
    private LocalTime startTime;
    private LocalTime endTime;
    private Double totalDistanceKm;
    private Integer estimatedTimeMin;
    private String status;
    private BusResponseDTO bus;
    private DriverResponseDTO driver;
    private ConductorResponseDTO conductor;
    private List<BusRouteStopResponseDTO> stops;   // ordered
    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BusStopResponseDTO getStartStop() {
        return startStop;
    }

    public void setStartStop(BusStopResponseDTO startStop) {
        this.startStop = startStop;
    }

    public BusStopResponseDTO getEndStop() {
        return endStop;
    }

    public void setEndStop(BusStopResponseDTO endStop) {
        this.endStop = endStop;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public Double getTotalDistanceKm() {
        return totalDistanceKm;
    }

    public void setTotalDistanceKm(Double totalDistanceKm) {
        this.totalDistanceKm = totalDistanceKm;
    }

    public Integer getEstimatedTimeMin() {
        return estimatedTimeMin;
    }

    public void setEstimatedTimeMin(Integer estimatedTimeMin) {
        this.estimatedTimeMin = estimatedTimeMin;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BusResponseDTO getBus() {
        return bus;
    }

    public void setBus(BusResponseDTO bus) {
        this.bus = bus;
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

    public List<BusRouteStopResponseDTO> getStops() {
        return stops;
    }

    public void setStops(List<BusRouteStopResponseDTO> stops) {
        this.stops = stops;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}