package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.model.Driver;
import com.bus.tracking.bus_tracking_system.repository.DriverRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {

    private final DriverRepository repo;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public DriverService(DriverRepository repo) {
        this.repo = repo;
    }

    public Driver addDriver(Driver driver) {
        driver.setPassword(encoder.encode(driver.getPassword()));
        return repo.save(driver);
    }

    public List<Driver> getAllDrivers() {
        return repo.findAll();
    }

    public Driver getDriverById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public Driver login(String phone, String rawPassword) {
        Driver driver = repo.findByPhone(phone);
        if (driver != null && encoder.matches(rawPassword, driver.getPassword())) {
            return driver;
        }
        return null;
    }
    // DriverService.java
    public void deleteDriver(Long id) {
        repo.deleteById(id);
    }
}
