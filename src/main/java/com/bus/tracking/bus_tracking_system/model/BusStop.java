package com.bus.tracking.bus_tracking_system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long busId;          // which bus this stop belongs to
    private String stopName;     // stop name (School, Mall, etc.)
    private double latitude;     // stop GPS latitude
    private double longitude;    // stop GPS longitude
    private int sequenceNumber;  // order of stops
    private boolean reached = false; // true if bus has reached
    private LocalDateTime reachedAt; // timestamp when reached

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getBusId() { return busId; }
    public void setBusId(Long busId) { this.busId = busId; }

    public String getStopName() { return stopName; }
    public void setStopName(String stopName) { this.stopName = stopName; }

    public double getLatitude() { return latitude; }
    public void setLatitude(double latitude) { this.latitude = latitude; }

    public double getLongitude() { return longitude; }
    public void setLongitude(double longitude) { this.longitude = longitude; }

    public int getSequenceNumber() { return sequenceNumber; }
    public void setSequenceNumber(int sequenceNumber) { this.sequenceNumber = sequenceNumber; }

    public boolean isReached() { return reached; }
    public void setReached(boolean reached) { this.reached = reached; }

    public LocalDateTime getReachedAt() { return reachedAt; }
    public void setReachedAt(LocalDateTime reachedAt) { this.reachedAt = reachedAt; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BusStop)) return false;
        BusStop busStop = (BusStop) o;
        return Objects.equals(id, busStop.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
