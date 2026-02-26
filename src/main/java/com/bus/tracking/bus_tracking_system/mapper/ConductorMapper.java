package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.*;
import com.bus.tracking.bus_tracking_system.model.Conductor;

public class ConductorMapper {

    public static Conductor toEntity(ConductorRequestDTO dto) {

        Conductor conductor = new Conductor();

        conductor.setName(dto.getName());
        conductor.setPhone(dto.getPhone());
        conductor.setEmployeeId(dto.getEmployeeId());
        conductor.setStatus(dto.getStatus());
        conductor.setJoiningDate(dto.getJoiningDate());
        conductor.setTerminateDate(dto.getTerminateDate());
        conductor.setLicenseExpiryDate(dto.getLicenseExpiryDate());

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
        dto.setEmployeeId(conductor.getEmployeeId());
        dto.setStatus(conductor.getStatus());

        return dto;
    }
}