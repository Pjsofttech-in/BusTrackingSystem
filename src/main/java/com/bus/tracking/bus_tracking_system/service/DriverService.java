package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.*;
import com.bus.tracking.bus_tracking_system.mapper.DriverMapper;
import com.bus.tracking.bus_tracking_system.model.Driver;
import com.bus.tracking.bus_tracking_system.repository.DriverRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    private final DriverRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public DriverService(DriverRepository repo) {
        this.repo = repo;
    }

    // CREATE
    @Transactional
    public DriverResponseDTO addDriver(DriverRequestDTO dto) {

        Driver driver = DriverMapper.toEntity(dto);

        if (driver.getPassword() != null && !driver.getPassword().isEmpty()) {
            driver.setPassword(encoder.encode(driver.getPassword()));
        }

        Driver saved = repo.save(driver);

        return DriverMapper.toDTO(saved);
    }

    // GET ALL
    public List<DriverResponseDTO> getAllDrivers() {
        return repo.findAll()
                .stream()
                .map(DriverMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public DriverResponseDTO getDriverById(Long id) {
        return repo.findById(id)
                .map(DriverMapper::toDTO)
                .orElse(null);
    }

    // UPDATE
    @Transactional
    public DriverResponseDTO updateDriver(Long id, DriverRequestDTO dto) {
        Driver existingDriver = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));

        // Update all fields
        existingDriver.setName(dto.getName());
        existingDriver.setPhone(dto.getPhone());
        existingDriver.setEmail(dto.getEmail());
        existingDriver.setLicenseNumber(dto.getLicenseNumber());
        existingDriver.setLicenseType(dto.getLicenseType());
        existingDriver.setLicensePhoto(dto.getLicensePhoto());
        existingDriver.setDriverPhoto(dto.getDriverPhoto());
        existingDriver.setExperienceYears(dto.getExperienceYears());
        existingDriver.setStatus(dto.getStatus());
        existingDriver.setLicenseExpiryDate(dto.getLicenseExpiryDate());
        existingDriver.setJoiningDate(dto.getJoiningDate());
        existingDriver.setTerminateDate(dto.getTerminateDate());
        existingDriver.setHouseNo(dto.getHouseNo());
        existingDriver.setStreet(dto.getStreet());
        existingDriver.setCity(dto.getCity());
        existingDriver.setState(dto.getState());
        existingDriver.setPincode(dto.getPincode());

        // Update password only if provided
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            existingDriver.setPassword(encoder.encode(dto.getPassword()));
        }

        Driver updated = repo.save(existingDriver);
        return DriverMapper.toDTO(updated);
    }

    // LOGIN
    public DriverLoginResponseDTO login(DriverLoginRequestDTO dto) {

        Driver driver = repo.findByPhone(dto.getPhone());

        if (driver != null &&
                encoder.matches(dto.getPassword(), driver.getPassword())) {

            return DriverMapper.toLoginDTO(driver);
        }

        return null;
    }

    // DELETE
    @Transactional
    public void deleteDriver(Long id) {
        repo.deleteById(id);
    }
}