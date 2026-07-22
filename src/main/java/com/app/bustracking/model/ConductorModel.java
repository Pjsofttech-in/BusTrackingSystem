package com.app.bustracking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "conductor")
public class ConductorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String phone;
    private String email;
    private String password;
    private String licenseNumber;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String idCard;

    private String licenseType;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String licensePhoto;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String conductorPhoto;

    private LocalDate licenseExpiryDate;
    private int experienceYears;
    private String status;

    private LocalDate joiningDate;
    private LocalDate terminateDate;

    private String houseNo;
    private String street;
    private String city;
    private String state;
    private String pincode;

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}