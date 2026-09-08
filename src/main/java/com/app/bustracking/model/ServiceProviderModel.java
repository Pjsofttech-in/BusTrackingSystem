package com.app.bustracking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "service_providers")
public class ServiceProviderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serviceprovidername;
    private String email;
    private String mobile;
    private String registrationNumber;
    private String address;
    private String status;  // e.g., ACTIVE, INACTIVE
}