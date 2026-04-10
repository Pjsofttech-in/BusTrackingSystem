package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class DriverResponseDTO {

    private Long id;
    private String name;
    private String phone;
    private String licenseNumber;
    private String employeeId;
    private int experienceYears;
    private String status;

    // Address
    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String pincode;

    // Extra fields
    private LocalDate licenseExpiryDate;
    private LocalDate joiningDate;
    private LocalDate terminateDate;
    private LocalDateTime createdAt;
}