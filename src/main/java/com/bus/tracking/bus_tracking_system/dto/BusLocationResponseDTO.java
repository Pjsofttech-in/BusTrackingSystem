package com.bus.tracking.bus_tracking_system.dto;

import java.time.LocalDateTime;

public class BusLocationResponseDTO {

    private Long id;
    private Long busId;
    private double latitude;
    private double longitude;
    private double speedKmph;
    private String direction;
    private LocalDateTime recordedAt;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBusId() {
        return busId;
    }

    public void setBusId(Long busId) {
        this.busId = busId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getSpeedKmph() {
        return speedKmph;
    }

    public void setSpeedKmph(double speedKmph) {
        this.speedKmph = speedKmph;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public LocalDateTime getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(LocalDateTime recordedAt) {
        this.recordedAt = recordedAt;
    }
}