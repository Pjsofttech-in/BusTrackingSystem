package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectionRequestDTO {
    private String name;
    private String abbreviation;
    private Double minDegrees;
    private Double maxDegrees;
    private String description;
}