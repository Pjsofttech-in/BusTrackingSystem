package com.bus.tracking.bus_tracking_system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class BusStop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    private String stopName;
    private double latitude;
    private double longitude;
    private int sequenceNumber;
    private boolean reached = false;
    private LocalDateTime reachedAt;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Bus getBus() {return bus;}
    public void setBus(Bus bus) {this.bus = bus;}
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
