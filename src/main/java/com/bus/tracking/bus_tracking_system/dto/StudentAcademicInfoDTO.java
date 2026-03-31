package com.bus.tracking.bus_tracking_system.dto;

import java.time.LocalDate;

public class StudentAcademicInfoDTO {

    private String studentClass;
    private String division;
    private String medium;
    private LocalDate academicYear;

    public StudentAcademicInfoDTO() {
    }

    public StudentAcademicInfoDTO(String studentClass,
                                  String division,
                                  String medium,
                                  LocalDate academicYear) {
        this.studentClass = studentClass;
        this.division = division;
        this.medium = medium;
        this.academicYear = academicYear;
    }

    public String getStudentClass() { return studentClass; }
    public String getDivision() { return division; }
    public String getMedium() { return medium; }
    public LocalDate getAcademicYear() { return academicYear; }
}