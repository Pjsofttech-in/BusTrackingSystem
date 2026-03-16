package com.bus.tracking.bus_tracking_system.dto;

public class RouteResponseDTO {

    private Long id;
    private String routeName;
    private String startPoint;
    private String endPoint;
    private double totalDistanceKm;
    private int estimatedTimeMin;
    private String status;
    private String busStop;

    // getters and setters

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

    public String getBusStop() {return busStop;
    }public void setBusStop(String busStop) {this.busStop = busStop;}
}