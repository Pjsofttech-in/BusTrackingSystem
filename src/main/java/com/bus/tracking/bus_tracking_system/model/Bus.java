package com.bus.tracking.bus_tracking_system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;

@Entity

public class Bus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @OneToOne
    @JoinColumn(name = "conductor_id")
    private Conductor conductor;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusLocation> locations;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BusStop> stops;

    @OneToMany(mappedBy = "bus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentScan> scans;

    private String busNumber;
    private String busType;
    private int capacity;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    //Getter and Setter
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getBusNumber() {return busNumber;}
    public void setBusNumber(String busNumber) {this.busNumber = busNumber;}
    public String getBusType() {return busType;}
    public void setBusType(String busType) {this.busType = busType;}
    public int getCapacity() {return capacity;}
    public void setCapacity(int capacity) {this.capacity = capacity;}
    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}
    public LocalDateTime getCreatedAt() {return createdAt;}
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt;}
    public LocalDateTime getUpdatedAt() {return updatedAt;}
    public void setUpdatedAt(LocalDateTime updatedAt) {this.updatedAt = updatedAt;}
    public Route getRoute() {return route;}
    public void setRoute(Route route) {this.route = route;}
    public Driver getDriver() {return driver;}
    public void setDriver(Driver driver) {this.driver = driver;}
    public Conductor getConductor() {return conductor;}
    public void setConductor(Conductor conductor) {this.conductor = conductor;}
    public List<BusLocation> getLocations() {return locations;}
    public void setLocations(List<BusLocation> locations) {this.locations = locations;}
    public List<BusStop> getStops() {return stops;}
    public void setStops(List<BusStop> stops) {this.stops = stops;}
    public List<StudentScan> getScans() {return scans;}
    public void setScans(List<StudentScan> scans) {this.scans = scans;}
}