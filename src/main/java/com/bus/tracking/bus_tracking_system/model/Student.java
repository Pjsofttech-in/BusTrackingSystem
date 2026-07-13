package com.bus.tracking.bus_tracking_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "stdid")
    private Long id;

    @Column(length = 50)
    private String name;
    @Column(length = 3)
    private String rollNumber;
    @Column(length = 50)
    private String admission;

    private boolean present;
    @Column(length = 10)
    private String status;
    private boolean inBus;

    @Lob
    private String qrImageUrl;

    @Column(length = 50)
    private String parentName;
    @Column(length = 15)
    private String parentPhone;
    @Column(length = 20)
    private String parentEmail;

    @Column(length = 5)
    private String bloodGroup;
    private Integer age;

    @Column(length = 10)
    private String studentClass;
    @Column(length = 2)
    private String division;

    @Column(length = 10)
    private String medium;
    @Column(length = 20)
    private String academicYear;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentFeePayment> payments;
}