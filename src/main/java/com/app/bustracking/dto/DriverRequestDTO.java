package com.app.bustracking.dto;

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
    private String idCard;          // base64 or string
    private String licenseType;
    private String licensePhoto;
    private String driverPhoto;
    private LocalDate licenseExpiryDate;
    private Integer experienceYears;
    private String status;
    private LocalDate joiningDate;
    private LocalDate terminateDate;
    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String pincode;
}