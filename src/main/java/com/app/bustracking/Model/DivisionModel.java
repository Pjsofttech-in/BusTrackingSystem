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
@Table(name = "division")
public class DivisionModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long divisionId;   // matches frontend

    @Column(nullable = false, unique = true)
    String divisionName;
}