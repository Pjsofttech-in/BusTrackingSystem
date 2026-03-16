package com.bus.tracking.bus_tracking_system.dto;

import java.time.LocalDate;
import java.util.Date;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;

import lombok.Getter;
import lombok.Setter;

public class ConductorResponseDTO {
    @Getter
    @Setter
    private Long id;
    private String name;
    private String phone;
    private String employeeId;
    private String status;
    private LocalDate joiningDate;
    private LocalDate terminateDate;
    private Date licenseExpiryDate;

    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String pincode;




}