package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DriverRequestDTO {

    private String name;
    private String phone;
    private String password;
    private String licenseNumber;
    private int experienceYears;
    private String status;
    private String employeeId;

    private LocalDate licenseExpiryDate; //added
    private LocalDate joiningDate;       // added
    private LocalDate terminateDate;     // added

    // Address
    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String pincode;
}