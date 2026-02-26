package com.bus.tracking.bus_tracking_system.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
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

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRollNumber() { return rollNumber; }
    public void setRollNumber(String rollNumber) { this.rollNumber = rollNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getQrImageUrl() { return qrImageUrl; }
    public void setQrImageUrl(String qrImageUrl) { this.qrImageUrl = qrImageUrl; }
    public String getParentName() { return parentName; }
    public void setParentName(String parentName) { this.parentName = parentName; }
    public String getParentPhone() { return parentPhone; }
    public void setParentPhone(String parentPhone) { this.parentPhone = parentPhone; }
    public String getParentEmail() { return parentEmail; }
    public void setParentEmail(String parentEmail) { this.parentEmail = parentEmail; }
    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public boolean isInBus() {
        return inBus;
    }
    public void setInBus(boolean inBus) {
        this.inBus = inBus;
    }
    public String getAdmission() {return admission;}
    public void setAdmission(String admission) {this.admission = admission;}
    public Integer getDivision() {return division;}
    public void setDivision(Integer division) {this.division = division;}
    public Integer getStudentClass() {return studentClass;}
    public void setStudentClass(Integer studentClass) {this.studentClass = studentClass;}
    public boolean isPresent() {return present;}
    public void setPresent(boolean present) {this.present = present;}
    public LocalDate getAcademicYear() {return academicYear;}
    public void setAcademicYear(LocalDate academicYear) {this.academicYear = academicYear;}
    public String getMedium() {return medium;}
    public void setMedium(String medium) {this.medium = medium;}
}
