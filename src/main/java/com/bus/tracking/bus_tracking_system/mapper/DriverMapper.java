package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.DriverRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.DriverResponseDTO;
import com.bus.tracking.bus_tracking_system.dto.DriverLoginResponseDTO;
import com.bus.tracking.bus_tracking_system.model.Driver;

public class DriverMapper {

    // RequestDTO → Entity
    public static Driver toEntity(DriverRequestDTO dto) {

        if (dto == null) return null;

        Driver driver = new Driver();

        driver.setName(dto.getName());
        driver.setPhone(dto.getPhone());
        driver.setPassword(dto.getPassword()); // encrypt in service
        driver.setLicenseNumber(dto.getLicenseNumber());
        driver.setExperienceYears(dto.getExperienceYears());
        driver.setStatus(dto.getStatus());

        // ✅ NEW FIELDS (important)
        driver.setLicenseExpiryDate(dto.getLicenseExpiryDate());
        driver.setJoiningDate(dto.getJoiningDate());
        driver.setTerminateDate(dto.getTerminateDate());

        // Address
        driver.setHouseNo(dto.getHouseNo());
        driver.setStreet(dto.getStreet());
        driver.setCity(dto.getCity());
        driver.setState(dto.getState());
        driver.setPincode(dto.getPincode());

        return driver;
    }

    // Entity → ResponseDTO
    public static DriverResponseDTO toDTO(Driver driver) {

        if (driver == null) return null;

        DriverResponseDTO dto = new DriverResponseDTO();

        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setPhone(driver.getPhone());
        dto.setLicenseNumber(driver.getLicenseNumber());
        dto.setEmployeeId(driver.getEmployeeId());
        dto.setExperienceYears(driver.getExperienceYears());
        dto.setStatus(driver.getStatus());


        // Address
        dto.setHouseNo(driver.getHouseNo());
        dto.setStreet(driver.getStreet());
        dto.setCity(driver.getCity());
        dto.setState(driver.getState());
        dto.setPincode(driver.getPincode());

        // Extra fields
        dto.setLicenseExpiryDate(driver.getLicenseExpiryDate());
        dto.setJoiningDate(driver.getJoiningDate());
        dto.setTerminateDate(driver.getTerminateDate());
        dto.setCreatedAt(driver.getCreatedAt());

        return dto;
    }

    // Entity → LoginResponseDTO
    public static DriverLoginResponseDTO toLoginDTO(Driver driver) {

        if (driver == null) return null;

        DriverLoginResponseDTO dto = new DriverLoginResponseDTO();

        dto.setId(driver.getId());
        dto.setName(driver.getName());
        dto.setPhone(driver.getPhone());
        dto.setStatus(driver.getStatus());

        return dto;
    }
}