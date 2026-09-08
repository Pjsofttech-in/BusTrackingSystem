package com.app.bustracking.dto;


import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;


@Getter
@Setter
public class BusResponseDTO {
    private Long id;
    private String busNumber;
    private String busType;
    private String busModelName;
    private Long serviceProviderId;
    private String serviceProviderName;
    private Integer mfgYear;
    private Integer capacity;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    // ✅ NEW: full service provider details
    private ServiceProviderResponseDTO serviceProvider;


    // ... existing getters and setters (Lombok handles them)


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public String getBusNumber() {
        return busNumber;
    }


    public void setBusNumber(String busNumber) {
        this.busNumber = busNumber;
    }


    public String getBusType() {
        return busType;
    }


    public void setBusType(String busType) {
        this.busType = busType;
    }


    public String getBusModelName() {
        return busModelName;
    }


    public void setBusModelName(String busModelName) {
        this.busModelName = busModelName;
    }


    public Long getServiceProviderId() {
        return serviceProviderId;
    }


    public void setServiceProviderId(Long serviceProviderId) {
        this.serviceProviderId = serviceProviderId;
    }


    public String getServiceProviderName() {
        return serviceProviderName;
    }


    public void setServiceProviderName(String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }


    public Integer getMfgYear() {
        return mfgYear;
    }


    public void setMfgYear(Integer mfgYear) {
        this.mfgYear = mfgYear;
    }


    public Integer getCapacity() {
        return capacity;
    }


    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }


    public String getStatus() {
        return status;
    }


    public void setStatus(String status) {
        this.status = status;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }


    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }


    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }


    public ServiceProviderResponseDTO getServiceProvider() {
        return serviceProvider;
    }


    public void setServiceProvider(ServiceProviderResponseDTO serviceProvider) {
        this.serviceProvider = serviceProvider;
    }
}

