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
@Table(name = "direction")
public class DirectionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    String name;          // "North", "South", etc.

    String abbreviation;  // "N", "S", etc.

    Double minDegrees;    // Starting degree (inclusive)
    Double maxDegrees;    // Ending degree (exclusive)

    @Column(columnDefinition = "TEXT")
    String description;
}