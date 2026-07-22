package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DirectionResponseDTO {
    private Long id;
    private String name;
    private String abbreviation;
    private Double minDegrees;
    private Double maxDegrees;
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public Double getMinDegrees() {
        return minDegrees;
    }

    public void setMinDegrees(Double minDegrees) {
        this.minDegrees = minDegrees;
    }

    public Double getMaxDegrees() {
        return maxDegrees;
    }

    public void setMaxDegrees(Double maxDegrees) {
        this.maxDegrees = maxDegrees;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}