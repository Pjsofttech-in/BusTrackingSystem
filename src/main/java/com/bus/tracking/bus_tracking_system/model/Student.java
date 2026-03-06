package com.bus.tracking.bus_tracking_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentFeePayment> payments;

    private String name;
    private String rollNumber;
    private String admission;
    private boolean present;
    private String status;
    private boolean inBus;
    private String qrImageUrl;
    private String parentName;
    private String parentPhone;
    private String parentEmail;
    private String bloodGroup;
    private Integer age;
    private Integer studentClass;
    private Integer division;
    private String medium;
    private LocalDate academicYear;

}
