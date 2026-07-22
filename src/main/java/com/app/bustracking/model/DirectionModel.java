package com.app.bustracking.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "direction")
public class DirectionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String abbreviation;
    private Double minDegrees;
    private Double maxDegrees;

    @Column(columnDefinition = "TEXT")
    private String description;
}