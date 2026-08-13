package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceProviderResponseDTO {
    private Long id;
    private String serviceprovidername;
    private String email;
    private String mobile;
    private String registrationNumber;
    private String address;
    private String status;
}