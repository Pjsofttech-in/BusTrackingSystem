package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ConductorResponseDTO {

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