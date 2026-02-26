package com.bus.tracking.bus_tracking_system.dto;

import java.time.LocalDate;

public class StudentAcademicInfoDTO {

    private Integer studentClass;
    private Integer division;
    private String medium;
    private LocalDate academicYear;

    public StudentAcademicInfoDTO() {
    }

    public StudentAcademicInfoDTO(Integer studentClass,
                                  Integer division,
                                  String medium,
                                  LocalDate academicYear) {
        this.studentClass = studentClass;
        this.division = division;
        this.medium = medium;
        this.academicYear = academicYear;
    }

    public Integer getStudentClass() { return studentClass; }
    public Integer getDivision() { return division; }
    public String getMedium() { return medium; }
    public LocalDate getAcademicYear() { return academicYear; }
}