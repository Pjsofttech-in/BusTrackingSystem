package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ConductorRequestDTO {
    private String name;
    private String phone;
    private String email;
    private String password;
    private String licenseNumber;
    private String idCard;
    private String licenseType;
    private String licensePhoto;
    private String conductorPhoto;
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

    @Getter
    @Setter
    public static class DirectionRequestDTO {
        private String name;
        private String abbreviation;
        private Double minDegrees;
        private Double maxDegrees;
        private String description;
    }
}