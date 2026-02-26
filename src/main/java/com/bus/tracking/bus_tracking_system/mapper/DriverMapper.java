package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.DriverRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.DriverResponseDTO;
import com.bus.tracking.bus_tracking_system.dto.DriverLoginResponseDTO;
import com.bus.tracking.bus_tracking_system.model.Driver;

public class DriverMapper {

    // RequestDTO → Entity
    public static Driver toEntity(DriverRequestDTO dto) {

        Driver driver = new Driver();

        driver.setName(dto.getName());
        driver.setPhone(dto.getPhone());
        driver.setPassword(dto.getPassword()); // Will be encrypted in service
        driver.setLicenseNumber(dto.getLicenseNumber());
        driver.setExperienceYears(dto.getExperienceYears());
        driver.setStatus(dto.getStatus());

        driver.setHouseNo(dto.getHouseNo());
        driver.setStreet(dto.getStreet());
        driver.setCity(dto.getCity());
        driver.setState(dto.getState());
        driver.setPincode(dto.getPincode());

        return driver;
    }

    // Entity → ResponseDTO
    public static DriverResponseDTO toDTO(Driver driver) {

        DriverResponseDTO dto = new DriverResponseDTO();

        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setPhone(driver.getPhone());
        dto.setLicenseNumber(driver.getLicenseNumber());
        dto.setExperienceYears(driver.getExperienceYears());
        dto.setStatus(driver.getStatus());

        return dto;
    }

    // Entity → LoginResponseDTO
    public static DriverLoginResponseDTO toLoginDTO(Driver driver) {

        DriverLoginResponseDTO dto = new DriverLoginResponseDTO();

        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setPhone(driver.getPhone());
        dto.setStatus(driver.getStatus());

        return dto;
    }
}