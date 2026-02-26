package com.bus.tracking.bus_tracking_system.dto;

public class BusLocationRequestDTO {

    private Long busId;
    private double latitude;
    private double longitude;
    private double speedKmph;
    private String direction;

    // getters and setters

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
}