package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class StudentResponseDTO {

    // Basic Info
    private Long id;
    private String name;
    private String rollNumber;
    private String admission;
    private Integer age;
    private String bloodGroup;

    // Parent Details
    private String parentName;
    private String parentPhone;
    private String parentEmail;

    // Academic Details
    private String studentClass;
    private String division;
    private String medium;
    private LocalDate academicYear;

    // Bus/System Fields
    private String qrImageUrl;
    private boolean inBus;
    private String status;
    private boolean present;
}