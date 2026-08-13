package com.app.bustracking.mapper;

import com.app.bustracking.dto.ConductorRequestDTO;
import com.app.bustracking.dto.ConductorResponseDTO;
import com.app.bustracking.model.ConductorModel;

public class ConductorMapper {

    public static ConductorModel toEntity(ConductorRequestDTO dto) {
        ConductorModel entity = new ConductorModel();
        entity.setName(dto.getName());
        entity.setPhone(dto.getPhone());
        entity.setEmail(dto.getEmail());
        entity.setPassword(dto.getPassword());
        entity.setLicenseNumber(dto.getLicenseNumber());
        entity.setIdCard(dto.getIdCard());

        entity.setLicensePhoto(dto.getLicensePhoto());
        entity.setConductorPhoto(dto.getConductorPhoto());
        entity.setLicenseExpiryDate(dto.getLicenseExpiryDate());
        entity.setExperienceYears(dto.getExperienceYears());
        entity.setStatus(dto.getStatus());
        entity.setJoiningDate(dto.getJoiningDate());
        entity.setTerminateDate(dto.getTerminateDate());
        entity.setHouseNo(dto.getHouseNo());
        entity.setStreet(dto.getStreet());
        entity.setCity(dto.getCity());
        entity.setState(dto.getState());
        entity.setPincode(dto.getPincode());
        return entity;
    }

    public static ConductorResponseDTO toDTO(ConductorModel entity) {
        ConductorResponseDTO dto = new ConductorResponseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPhone(entity.getPhone());
        dto.setEmail(entity.getEmail());
        dto.setLicenseNumber(entity.getLicenseNumber());
        dto.setIdCard(entity.getIdCard());

        dto.setLicensePhoto(entity.getLicensePhoto());
        dto.setConductorPhoto(entity.getConductorPhoto());
        dto.setLicenseExpiryDate(entity.getLicenseExpiryDate());
        dto.setExperienceYears(entity.getExperienceYears());
        dto.setStatus(entity.getStatus());
        dto.setJoiningDate(entity.getJoiningDate());
        dto.setTerminateDate(entity.getTerminateDate());
        dto.setHouseNo(entity.getHouseNo());
        dto.setStreet(entity.getStreet());
        dto.setCity(entity.getCity());
        dto.setState(entity.getState());
        dto.setPincode(entity.getPincode());
        dto.setCreatedAt(entity.getCreatedAt());
        return dto;
    }
}