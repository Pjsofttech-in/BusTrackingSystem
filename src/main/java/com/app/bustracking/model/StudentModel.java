// src/main/java/com/app/bustracking/model/StudentModel.java
package com.app.bustracking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "student")
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String rollNumber;

    private String admission;
    private Boolean present = false;
    private String status = "ACTIVE";
    private Boolean inBus = false;

    @Column(columnDefinition = "TEXT")
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "route_id")
    private BusRouteModel route;

    // ✅ NEW: password field for login
    private String password;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentFeePaymentModel> payments = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<StudentScanModel> scans = new ArrayList<>();
}