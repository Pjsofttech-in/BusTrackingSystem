package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.*;
import com.bus.tracking.bus_tracking_system.mapper.DriverMapper;
import com.bus.tracking.bus_tracking_system.model.Driver;
import com.bus.tracking.bus_tracking_system.repository.DriverRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

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
    public DriverResponseDTO addDriver(DriverRequestDTO dto) {

        Driver driver = DriverMapper.toEntity(dto);

        if (driver.getPassword() != null) {
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
    public void deleteDriver(Long id) {
        repo.deleteById(id);
    }
}