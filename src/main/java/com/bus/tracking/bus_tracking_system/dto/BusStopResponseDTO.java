package com.bus.tracking.bus_tracking_system.dto;

import java.time.LocalDateTime;

public class BusStopResponseDTO {

    private Long id;
    private String stopName;
    private double latitude;
    private double longitude;
    private int sequenceNumber;
    private boolean reached;
    private LocalDateTime reachedAt;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
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

    public int getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(int sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public boolean isReached() {
        return reached;
    }

    public void setReached(boolean reached) {
        this.reached = reached;
    }

    public LocalDateTime getReachedAt() {
        return reachedAt;
    }

    public void setReachedAt(LocalDateTime reachedAt) {
        this.reachedAt = reachedAt;
    }
}