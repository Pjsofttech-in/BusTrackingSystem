package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Getter
@Setter
public class StudentRequestDTO {

    private String name;
    private String rollNumber;
    private String admission;
    private Integer age;
    private String bloodGroup;

    private String parentName;
    private String parentPhone;
    private String parentEmail;

    private Integer studentClass;
    private Integer division;
    private String medium;
    private LocalDate academicYear;

}