package com.app.bustracking.mapper;

import com.app.bustracking.dto.ServiceProviderRequestDTO;
import com.app.bustracking.dto.ServiceProviderResponseDTO;
import com.app.bustracking.model.ServiceProviderModel;

public class ServiceProviderMapper {

    public static ServiceProviderModel toEntity(ServiceProviderRequestDTO dto) {
        ServiceProviderModel entity = new ServiceProviderModel();
        entity.setServiceprovidername(dto.getServiceprovidername());
        entity.setEmail(dto.getEmail());
        entity.setMobile(dto.getMobile());
        entity.setRegistrationNumber(dto.getRegistrationNumber());
        entity.setAddress(dto.getAddress());
        entity.setStatus(dto.getStatus() != null ? dto.getStatus() : "ACTIVE");
        return entity;
    }

    public static ServiceProviderResponseDTO toDTO(ServiceProviderModel entity) {
        ServiceProviderResponseDTO dto = new ServiceProviderResponseDTO();
        dto.setId(entity.getId());
        dto.setServiceprovidername(entity.getServiceprovidername());
        dto.setEmail(entity.getEmail());
        dto.setMobile(entity.getMobile());
        dto.setRegistrationNumber(entity.getRegistrationNumber());
        dto.setAddress(entity.getAddress());
        dto.setStatus(entity.getStatus());
        return dto;
    }
}