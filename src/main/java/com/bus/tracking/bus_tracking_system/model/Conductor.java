package com.bus.tracking.bus_tracking_system.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "conductor")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Conductor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "phone", nullable = false, length = 10)
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "employee_id", nullable = false, unique = true)
    private String employeeId;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "joining_date")
    private LocalDate joiningDate;

    @Column(name = "terminate_date")
    private LocalDate terminateDate;

    @Column(name = "house_no")
    private String houseNo;

    @Column(name = "street")
    private String street;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "pincode")
    private String pincode;

    @Column(name = "license_expiry_date")
    @Temporal(TemporalType.DATE)
    private Date licenseExpiryDate;

    @Lob
    @Column(name = "license_photo", columnDefinition = "LONGTEXT")
    private String licensePhoto;

    @Lob
    @Column(name = "conductor_photo", columnDefinition = "LONGTEXT")
    private String conductorPhoto;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}