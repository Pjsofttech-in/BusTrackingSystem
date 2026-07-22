package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentRequestDTO {
    private String name;
    private String rollNumber;
    private String admission;
    private Boolean present;
    private String status;
    private Boolean inBus;
    private String qrImageUrl;
    private String parentName;
    private String parentPhone;
    private String parentEmail;
    private String bloodGroup;
    private Integer age;
    private String studentClass;
    private String division;
    private String medium;
    private String academicYear;
}