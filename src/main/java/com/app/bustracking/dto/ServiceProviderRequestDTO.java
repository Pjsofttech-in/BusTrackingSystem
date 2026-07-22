package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceProviderRequestDTO {
    private String serviceprovidername;
    private String email;
    private String mobile;
    private String city;
    private String state;
    private String pincode;
}