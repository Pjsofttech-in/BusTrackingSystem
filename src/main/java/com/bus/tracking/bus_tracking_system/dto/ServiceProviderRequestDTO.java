package com.bus.tracking.bus_tracking_system.dto;

public class ServiceProviderRequestDTO {

    private String name;
    private String mobile;
    private String email;
    //private String address;
    private String state;
    private String city;
    private String pincode;
    // getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}