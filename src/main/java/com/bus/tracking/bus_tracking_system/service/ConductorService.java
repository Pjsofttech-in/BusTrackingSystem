package com.bus.tracking.bus_tracking_system.service;

import com.bus.tracking.bus_tracking_system.dto.ConductorRequestDTO;
import com.bus.tracking.bus_tracking_system.dto.ConductorResponseDTO;
import com.bus.tracking.bus_tracking_system.dto.ConductorStatisticsDTO;
import com.bus.tracking.bus_tracking_system.mapper.ConductorMapper;
import com.bus.tracking.bus_tracking_system.model.Conductor;
import com.bus.tracking.bus_tracking_system.repository.ConductorRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ConductorService {

    private final ConductorRepository repo;

    public ConductorService(ConductorRepository repo) {
        this.repo = repo;
    }

    // CREATE
    @Transactional
    public ConductorResponseDTO addConductor(ConductorRequestDTO dto) {
        Conductor conductor = ConductorMapper.toEntity(dto);
        Conductor saved = repo.save(conductor);
        return ConductorMapper.toDTO(saved);
    }

    // GET ALL
    public List<ConductorResponseDTO> getAllConductors() {
        return repo.findAll()
                .stream()
                .map(ConductorMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public ConductorResponseDTO getConductorById(Long id) {
        Conductor conductor = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor not found with id: " + id));
        return ConductorMapper.toDTO(conductor);
    }

    // GET BY STATUS
    public List<ConductorResponseDTO> getConductorsByStatus(String status) {
        return repo.findByStatus(status)
                .stream()
                .map(ConductorMapper::toDTO)
                .collect(Collectors.toList());
    }

    // GET EXPIRED LICENSES
    public List<ConductorResponseDTO> getExpiredLicenses() {
        Date today = new Date();
        return repo.findExpiredLicenses(today)
                .stream()
                .map(ConductorMapper::toDTO)
                .collect(Collectors.toList());
    }

    // UPDATE
    @Transactional
    public ConductorResponseDTO updateConductor(Long id, ConductorRequestDTO dto) {
        Conductor existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor not found with id: " + id));

        existing.setName(dto.getName());
        existing.setPhone(dto.getPhone());
        existing.setEmail(dto.getEmail());
        existing.setEmployeeId(dto.getEmployeeId());
        existing.setStatus(dto.getStatus());
        existing.setJoiningDate(dto.getJoiningDate());
        existing.setTerminateDate(dto.getTerminateDate());
        existing.setLicenseExpiryDate(dto.getLicenseExpiryDate());
        existing.setLicensePhoto(dto.getLicensePhoto());
        existing.setConductorPhoto(dto.getConductorPhoto());
        existing.setHouseNo(dto.getHouseNo());
        existing.setStreet(dto.getStreet());
        existing.setCity(dto.getCity());
        existing.setState(dto.getState());
        existing.setPincode(dto.getPincode());

        Conductor updated = repo.save(existing);
        return ConductorMapper.toDTO(updated);
    }

    // UPDATE CONDUCTOR PHOTO
    @Transactional
    public ConductorResponseDTO updateConductorPhoto(Long id, String photoBase64) {
        Conductor existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor not found with id: " + id));

        existing.setConductorPhoto(photoBase64);
        Conductor updated = repo.save(existing);
        return ConductorMapper.toDTO(updated);
    }

    // UPDATE LICENSE PHOTO
    @Transactional
    public ConductorResponseDTO updateLicensePhoto(Long id, String photoBase64) {
        Conductor existing = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Conductor not found with id: " + id));

        existing.setLicensePhoto(photoBase64);
        Conductor updated = repo.save(existing);
        return ConductorMapper.toDTO(updated);
    }

    // DELETE
    @Transactional
    public void deleteConductor(Long id) {
        repo.deleteById(id);
    }

    // GET STATISTICS
    public ConductorStatisticsDTO getStatistics() {
        List<Conductor> all = repo.findAll();
        Date today = new Date();

        ConductorStatisticsDTO stats = new ConductorStatisticsDTO();
        stats.setTotal(all.size());
        stats.setActive(all.stream().filter(c -> "Join".equals(c.getStatus())).count());
        stats.setSuspended(all.stream().filter(c -> "Suspended".equals(c.getStatus())).count());
        stats.setTerminated(all.stream().filter(c -> "Terminated".equals(c.getStatus())).count());
        stats.setWithEmail(all.stream().filter(c -> c.getEmail() != null && !c.getEmail().isEmpty()).count());
        stats.setExpiredLicenses(all.stream()
                .filter(c -> c.getLicenseExpiryDate() != null && c.getLicenseExpiryDate().before(today))
                .count());

        // Group by city
        Map<String, Long> byCity = all.stream()
                .filter(c -> c.getCity() != null && !c.getCity().isEmpty())
                .collect(Collectors.groupingBy(
                        Conductor::getCity,
                        Collectors.counting()
                ));
        stats.setByCity(byCity);

        // Group by status
        Map<String, Long> byStatus = all.stream()
                .collect(Collectors.groupingBy(
                        c -> c.getStatus() != null ? c.getStatus() : "Unknown",
                        Collectors.counting()
                ));
        stats.setByStatus(byStatus);

        return stats;
    }

    // SEARCH CONDUCTORS
    public List<ConductorResponseDTO> searchConductors(String name, String phone, String email,
                                                       String employeeId, String city, String status) {
        List<Conductor> all = repo.findAll();

        return all.stream()
                .filter(c -> name == null || c.getName().toLowerCase().contains(name.toLowerCase()))
                .filter(c -> phone == null || c.getPhone().contains(phone))
                .filter(c -> email == null || (c.getEmail() != null && c.getEmail().toLowerCase().contains(email.toLowerCase())))
                .filter(c -> employeeId == null || c.getEmployeeId().toLowerCase().contains(employeeId.toLowerCase()))
                .filter(c -> city == null || (c.getCity() != null && c.getCity().toLowerCase().contains(city.toLowerCase())))
                .filter(c -> status == null || c.getStatus().equals(status))
                .map(ConductorMapper::toDTO)
                .collect(Collectors.toList());
    }
}