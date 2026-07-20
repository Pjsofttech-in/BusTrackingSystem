// src/main/java/com/app/bustracking/request/ConductorRequest.java
package com.app.bustracking.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ConductorRequest(
        @NotBlank String name,
        @NotBlank @Size(min = 10, max = 10) String phone,
        @Email String email,
        @NotBlank String password,
        String licenseNumber,
        String idCard,
        String licenseType,
        String licensePhoto,
        String conductorPhoto,
        @Future LocalDate licenseExpiryDate,   // must be future
        int experienceYears,
        String status,
        LocalDate joiningDate,
        LocalDate terminateDate,

        // Address
        String houseNo,
        String street,
        String city,
        String state,
        String pincode
) {}