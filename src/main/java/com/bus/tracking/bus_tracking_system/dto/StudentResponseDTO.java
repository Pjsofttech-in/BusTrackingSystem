package com.bus.tracking.bus_tracking_system.dto;

public class StudentResponseDTO {

    private Long id;
    private String name;
    private String rollNumber;
    private String qrImageUrl;
    private boolean inBus;
    private String status;

    // getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getQrImageUrl() {
        return qrImageUrl;
    }

    public void setQrImageUrl(String qrImageUrl) {
        this.qrImageUrl = qrImageUrl;
    }

    public boolean isInBus() {
        return inBus;
    }

    public void setInBus(boolean inBus) {
        this.inBus = inBus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}