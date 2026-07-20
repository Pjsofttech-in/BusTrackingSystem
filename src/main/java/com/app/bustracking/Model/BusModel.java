package com.app.bustracking.Model;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import java.time.LocalDateTime;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
@Table(name = "bus")
public class BusModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String busNumber;
    String busType;
    String busModelName;                       // renamed from serviceprovidername

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "service_provider_id")
    ServiceProviderModel serviceProvider;      // new relation

    int mfgYear;
    int capacity;
    String status;

    LocalDateTime createdAt;
    LocalDateTime updatedAt;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void onUpdate() {
        updatedAt = LocalDateTime.now();
    }
}