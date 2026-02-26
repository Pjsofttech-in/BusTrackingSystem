package com.bus.tracking.bus_tracking_system.dto;

public class DriverLoginRequestDTO {

    private String phone;
    private String password;

    // getters and setters

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}