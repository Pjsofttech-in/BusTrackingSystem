package com.app.bustracking.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DriverResponse(
        Long id,                    // include ID in response
        String name,
        String phone,
        String email,
        String licenseNumber,
        String idCard,
        String licenseType,
        String licensePhoto,
        String driverPhoto,
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