package com.app.bustracking.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "student")
public class StudentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false)
    String name;

    @Column(nullable = false, unique = true)
    String rollNumber;

    String admission;        // date string

    Boolean present = false;
    String status = "ACTIVE";
    Boolean inBus = false;

    @Column(columnDefinition = "TEXT")
    String qrImageUrl;

    String parentName;
    String parentPhone;
    String parentEmail;

    String bloodGroup;
    Integer age;

    String studentClass;     // e.g., "Class 10 - Science"
    String division;
    String medium;
    String academicYear;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    List<StudentFeePaymentModel> payments = new ArrayList<>();

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL, orphanRemoval = true)
    List<StudentScanModel> scans = new ArrayList<>();
}