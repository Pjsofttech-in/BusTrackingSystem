package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DriverRequestDTO {

    private String name;
    private String phone;
    private String email;
    private String password;
    private String licenseNumber;
    private String licenseType;
    private String licensePhoto;
    private String driverPhoto;
    private int experienceYears;
    private String status;
    private String employeeId;

    private LocalDate licenseExpiryDate;
    private LocalDate joiningDate;
    private LocalDate terminateDate;

    // Address
    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String pincode;
}