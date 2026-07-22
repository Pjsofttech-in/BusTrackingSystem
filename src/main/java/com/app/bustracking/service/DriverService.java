package com.app.bustracking.service;

import com.app.bustracking.dto.DriverRequestDTO;
import com.app.bustracking.dto.DriverResponseDTO;
import com.app.bustracking.mapper.DriverMapper;
import com.app.bustracking.model.DriverModel;
import com.app.bustracking.repository.DriverRepository;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DriverService {

    private final DriverRepository repository;
//    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public DriverService(DriverRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public DriverResponseDTO create(DriverRequestDTO dto) {
        DriverModel driver = DriverMapper.toEntity(dto);
//        if (driver.getPassword() != null && !driver.getPassword().isEmpty()) {
//            driver.setPassword(encoder.encode(driver.getPassword()));
//        }
        DriverModel saved = repository.save(driver);
        return DriverMapper.toDTO(saved);
    }

    public List<DriverResponseDTO> getAll() {
        return repository.findAll().stream()
                .map(DriverMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DriverResponseDTO getById(Long id) {
        return repository.findById(id)
                .map(DriverMapper::toDTO)
                .orElse(null);
    }

    @Transactional
    public DriverResponseDTO update(Long id, DriverRequestDTO dto) {
        DriverModel driver = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
        driver.setName(dto.getName());
        driver.setPhone(dto.getPhone());
        driver.setEmail(dto.getEmail());
        driver.setLicenseNumber(dto.getLicenseNumber());
        driver.setIdCard(dto.getIdCard());
        driver.setLicenseType(dto.getLicenseType());
        driver.setLicensePhoto(dto.getLicensePhoto());
        driver.setDriverPhoto(dto.getDriverPhoto());
        driver.setLicenseExpiryDate(dto.getLicenseExpiryDate());
        driver.setExperienceYears(dto.getExperienceYears());
        driver.setStatus(dto.getStatus());
        driver.setJoiningDate(dto.getJoiningDate());
        driver.setTerminateDate(dto.getTerminateDate());
        driver.setHouseNo(dto.getHouseNo());
        driver.setStreet(dto.getStreet());
        driver.setCity(dto.getCity());
        driver.setState(dto.getState());
        driver.setPincode(dto.getPincode());
//        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
//            driver.setPassword(encoder.encode(dto.getPassword()));
//        }
        DriverModel updated = repository.save(driver);
        return DriverMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        repository.deleteById(id);
    }

    public DriverModel findEntityById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Driver not found"));
    }
}