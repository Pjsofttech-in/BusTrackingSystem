// src/main/java/com/app/bustracking/response/ConductorResponse.java
package com.app.bustracking.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ConductorResponse(
        Long id,
        String name,
        String phone,
        String email,
        String licenseNumber,
        String idCard,
        String licenseType,
        String licensePhoto,
        String conductorPhoto,
        LocalDate licenseExpiryDate,
        int experienceYears,
        String status,
        LocalDate joiningDate,
        LocalDate terminateDate,

        // Address
        String houseNo,
        String street,
        String city,
        String state,
        String pincode,
        LocalDateTime createdAt
) {}