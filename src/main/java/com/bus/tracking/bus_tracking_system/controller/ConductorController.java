package com.bus.tracking.bus_tracking_system.controller;

import com.bus.tracking.bus_tracking_system.dto.ConductorRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.ConductorResponseDTO;
import com.bus.tracking.bus_tracking_system.dto.ConductorStatisticsDTO;
import com.bus.tracking.bus_tracking_system.service.ConductorService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/conductor")
public class ConductorController {

    private final ConductorService service;

    public ConductorController(ConductorService service) {
        this.service = service;
    }

    /**
     * ADD CONDUCTOR
     * POST /conductor/add
     */
    @PostMapping("/add")
    public ResponseEntity<?> addConductor(@RequestBody ConductorRequestDTO dto) {
        try {
            // Log incoming data for debugging
            System.out.println("Received conductor data: " + dto);
            System.out.println("Name: " + dto.getName());
            System.out.println("Phone: " + dto.getPhone());
            System.out.println("Email: " + dto.getEmail());
            System.out.println("EmployeeId: " + dto.getEmployeeId());
            System.out.println("Status: " + dto.getStatus());

            ConductorResponseDTO response = service.addConductor(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    /**
     * GET ALL CONDUCTORS
     * GET /conductor/all
     */
    @GetMapping("/all")
    public ResponseEntity<List<ConductorResponseDTO>> getAllConductors() {
        List<ConductorResponseDTO> conductors = service.getAllConductors();
        return ResponseEntity.ok(conductors);
    }

    /**
     * GET CONDUCTOR BY ID
     * GET /conductor/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ConductorResponseDTO> getConductor(@PathVariable Long id) {
        ConductorResponseDTO conductor = service.getConductorById(id);
        return ResponseEntity.ok(conductor);
    }

    /**
     * GET CONDUCTORS BY STATUS
     * GET /conductor/status/{status}
     */
    @GetMapping("/status/{status}")
    public ResponseEntity<List<ConductorResponseDTO>> getConductorsByStatus(@PathVariable String status) {
        List<ConductorResponseDTO> conductors = service.getConductorsByStatus(status);
        return ResponseEntity.ok(conductors);
    }

    /**
     * GET CONDUCTORS WITH EXPIRED LICENSES
     * GET /conductor/expired-licenses
     */
    @GetMapping("/expired-licenses")
    public ResponseEntity<List<ConductorResponseDTO>> getExpiredLicenses() {
        List<ConductorResponseDTO> conductors = service.getExpiredLicenses();
        return ResponseEntity.ok(conductors);
    }

    /**
     * UPDATE CONDUCTOR
     * PUT /conductor/update/{id}
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateConductor(
            @PathVariable Long id,
            @RequestBody ConductorRequestDTO dto) {
        try {
            ConductorResponseDTO updated = service.updateConductor(id, dto);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    /**
     * DELETE CONDUCTOR
     * DELETE /conductor/delete/{id}
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteConductor(@PathVariable Long id) {
        service.deleteConductor(id);
        return ResponseEntity.ok("Conductor deleted successfully");
    }

    /**
     * UPLOAD CONDUCTOR PHOTO
     * POST /conductor/upload-photo/{id}
     */
    @PostMapping("/upload-photo/{id}")
    public ResponseEntity<?> uploadConductorPhoto(
            @PathVariable Long id,
            @RequestParam("photo") MultipartFile file) throws IOException {

        try {
            String base64Photo = Base64.getEncoder().encodeToString(file.getBytes());
            ConductorResponseDTO updated = service.updateConductorPhoto(id, base64Photo);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    /**
     * UPLOAD LICENSE PHOTO
     * POST /conductor/upload-license/{id}
     */
    @PostMapping("/upload-license/{id}")
    public ResponseEntity<?> uploadLicensePhoto(
            @PathVariable Long id,
            @RequestParam("licensePhoto") MultipartFile file) throws IOException {

        try {
            String base64Photo = Base64.getEncoder().encodeToString(file.getBytes());
            ConductorResponseDTO updated = service.updateLicensePhoto(id, base64Photo);
            return ResponseEntity.ok(updated);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error: " + e.getMessage());
        }
    }

    /**
     * GET CONDUCTOR STATISTICS
     * GET /conductor/stats
     */
    @GetMapping("/stats")
    public ResponseEntity<ConductorStatisticsDTO> getConductorStatistics() {
        ConductorStatisticsDTO stats = service.getStatistics();
        return ResponseEntity.ok(stats);
    }

    /**
     * SEARCH CONDUCTORS
     * GET /conductor/search
     */
    @GetMapping("/search")
    public ResponseEntity<List<ConductorResponseDTO>> searchConductors(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String phone,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String employeeId,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String status) {

        List<ConductorResponseDTO> results = service.searchConductors(name, phone, email, employeeId, city, status);
        return ResponseEntity.ok(results);
    }
}