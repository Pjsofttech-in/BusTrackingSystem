package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.ConductorRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.ConductorResponseDTO;
import com.bus.tracking.bus_tracking_system.model.Conductor;

public class ConductorMapper {

    public static Conductor toEntity(ConductorRequestDTO dto) {
        Conductor conductor = new Conductor();

        conductor.setName(dto.getName());
        conductor.setPhone(dto.getPhone());
        conductor.setEmail(dto.getEmail());
        conductor.setEmployeeId(dto.getEmployeeId());
        conductor.setStatus(dto.getStatus());
        conductor.setJoiningDate(dto.getJoiningDate());
        conductor.setTerminateDate(dto.getTerminateDate());
        conductor.setLicenseExpiryDate(dto.getLicenseExpiryDate());
        conductor.setLicensePhoto(dto.getLicensePhoto());
        conductor.setConductorPhoto(dto.getConductorPhoto());
        conductor.setHouseNo(dto.getHouseNo());
        conductor.setStreet(dto.getStreet());
        conductor.setCity(dto.getCity());
        conductor.setState(dto.getState());
        conductor.setPincode(dto.getPincode());

        return conductor;
    }

    public static ConductorResponseDTO toDTO(Conductor conductor) {
        ConductorResponseDTO dto = new ConductorResponseDTO();

        dto.setId(conductor.getId());
        dto.setName(conductor.getName());
        dto.setPhone(conductor.getPhone());
        dto.setEmail(conductor.getEmail());
        dto.setEmployeeId(conductor.getEmployeeId());
        dto.setStatus(conductor.getStatus());
        dto.setJoiningDate(conductor.getJoiningDate());
        dto.setTerminateDate(conductor.getTerminateDate());
        dto.setLicenseExpiryDate(conductor.getLicenseExpiryDate());
        dto.setLicensePhoto(conductor.getLicensePhoto());
        dto.setConductorPhoto(conductor.getConductorPhoto());
        dto.setHouseNo(conductor.getHouseNo());
        dto.setStreet(conductor.getStreet());
        dto.setCity(conductor.getCity());
        dto.setState(conductor.getState());
        dto.setPincode(conductor.getPincode());

        return dto;
    }
}