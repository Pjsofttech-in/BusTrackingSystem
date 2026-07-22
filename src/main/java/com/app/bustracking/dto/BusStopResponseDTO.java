package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class BusStopResponseDTO {
    private Long id;
    private BusResponseDTO bus;      // or just busId
    private String stopName;
    private Double latitude;
    private Double longitude;
    private Boolean reached;
    private LocalDateTime reachedAt;

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

    public String getStopName() {
        return stopName;
    }

    public void setStopName(String stopName) {
        this.stopName = stopName;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Boolean getReached() {
        return reached;
    }

    public void setReached(Boolean reached) {
        this.reached = reached;
    }

    public LocalDateTime getReachedAt() {
        return reachedAt;
    }

    public void setReachedAt(LocalDateTime reachedAt) {
        this.reachedAt = reachedAt;
    }
}