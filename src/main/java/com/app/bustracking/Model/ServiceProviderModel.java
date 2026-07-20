package com.app.bustracking.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "service_providers")
public class ServiceProviderModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String serviceprovidername;  // renamed from "name"
    String email;
    String mobile;
    String city;
    String state;
    String pincode;
}