package com.app.bustracking.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.time.LocalDate;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "conductor")
public class ConductorModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String phone;
    String email;
    String password;
    String licenseNumber;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    String idCard;

    String licenseType;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    String licensePhoto;

    @Lob
    @Column(columnDefinition = "LONGTEXT")
    String conductorPhoto;

    LocalDate licenseExpiryDate;
    int experienceYears;
    String status;

    LocalDate joiningDate;
    LocalDate terminateDate;

    // Address
    String houseNo;
    String street;
    String city;
    String state;
    String pincode;

    LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}