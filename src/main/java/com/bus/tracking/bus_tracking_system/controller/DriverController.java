package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.model.Driver;
import com.bus.tracking.bus_tracking_system.service.DriverService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/driver")
@CrossOrigin(origins = "https://pjsofttech.com")
public class DriverController {

    private final DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @PostMapping("/add")
    public Driver addDriver(@RequestBody Driver driver) {
        return service.addDriver(driver);
    }

    @GetMapping("/all")
    public List<Driver> getAllDrivers() {
        return service.getAllDrivers();
    }

    @GetMapping("/{id}")
    public Driver getDriver(@PathVariable Long id) {
        return service.getDriverById(id);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> data) {
        Driver driver = service.login(data.get("phone"), data.get("password"));

        if (driver == null) {
            return ResponseEntity.status(401).body("Invalid credentials");
        }

        driver.setPassword(null); // VERY IMPORTANT
        return ResponseEntity.ok(driver);
    }

    @PutMapping("/update/{id}")
    public Driver updateDriver(@PathVariable Long id, @RequestBody Driver updated) {
        Driver existing = service.getDriverById(id);
        if (existing == null) return null;

        existing.setName(updated.getName());
        existing.setPhone(updated.getPhone());
        existing.setStatus(updated.getStatus());
        existing.setLicenseNumber(updated.getLicenseNumber());
        existing.setExperienceYears(updated.getExperienceYears());

        existing.setHouseNo(updated.getHouseNo());
        existing.setStreet(updated.getStreet());
        existing.setCity(updated.getCity());
        existing.setState(updated.getState());
        existing.setPincode(updated.getPincode());

        return service.addDriver(existing);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteDriver(@PathVariable Long id) {
        service.deleteDriver(id);
        return "Driver deleted";
    }

}
