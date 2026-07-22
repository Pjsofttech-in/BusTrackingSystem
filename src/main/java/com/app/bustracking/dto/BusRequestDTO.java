package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusRequestDTO {
    private String busNumber;
    private String busType;
    private String busModelName;
    private Long serviceProviderId;   // relation
    private Integer mfgYear;
    private Integer capacity;
    private String status;
}