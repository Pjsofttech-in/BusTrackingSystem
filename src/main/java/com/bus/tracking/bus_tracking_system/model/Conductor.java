package com.bus.tracking.bus_tracking_system.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String employeeId;
    private String status;
    private LocalDate joiningDate;
    private LocalDate terminateDate;
    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String pincode;
    private Date licenseExpiryDate;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    //Getter and Setter
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
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getHouseNo() {
        return houseNo;
    }
    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo;
    }
    public String getStreet() {
        return street;
    }
    public void setStreet(String street) {
        this.street = street;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getPincode() {
        return pincode;
    }
    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public LocalDate getJoiningDate() {return joiningDate;}
    public void setJoiningDate(LocalDate joiningDate) {this.joiningDate = joiningDate;}
    public LocalDate getTerminateDate() {return terminateDate;}
    public void setTerminateDate(LocalDate terminateDate) {this.terminateDate = terminateDate;}
    public Date getLicenseExpiryDate() {return licenseExpiryDate;}
    public void setLicenseExpiryDate(Date licenseExpiryDate) {this.licenseExpiryDate = licenseExpiryDate;}
}
