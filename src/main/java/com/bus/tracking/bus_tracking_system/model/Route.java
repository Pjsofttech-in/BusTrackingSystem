package com.bus.tracking.bus_tracking_system.model;

import jakarta.persistence.*;

@Entity
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String routeName;
    private String startTime;
    private String stopTime;
    private String startPoint;
    private String endPoint;
    private double totalDistanceKm;
    private int estimatedTimeMin;
    private String status;

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
    public String getStartPoint() {
        return startPoint;
    }
    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }
    public String getEndPoint() {
        return endPoint;
    }
    public void setEndPoint(String endPoint) {
        this.endPoint = endPoint;
    }
    public double getTotalDistanceKm() {
        return totalDistanceKm;
    }
    public void setTotalDistanceKm(double totalDistanceKm) {
        this.totalDistanceKm = totalDistanceKm;
    }
    public int getEstimatedTimeMin() {
        return estimatedTimeMin;
    }
    public void setEstimatedTimeMin(int estimatedTimeMin) {
        this.estimatedTimeMin = estimatedTimeMin;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getStartTime() {return startTime;}
    public void setStartTime(String startTime) {this.startTime = startTime;}
    public String getStopTime() {return stopTime;}
    public void setStopTime(String stopTime) {this.stopTime = stopTime;}
}
