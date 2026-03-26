package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceProviderResponseDTO {

    private Long id;
    private String name;
    private String mobile;
    private String email;

    private String state;
    private String city;
    private String pincode;
}