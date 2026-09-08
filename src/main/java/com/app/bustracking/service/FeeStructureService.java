package com.app.bustracking.service;

import com.app.bustracking.dto.FeeStructureRequestDTO;
import com.app.bustracking.dto.FeeStructureResponseDTO;
import com.app.bustracking.exception.ResourceNotFoundException;
import com.app.bustracking.mapper.FeeStructureMapper;
import com.app.bustracking.model.AcademicYearModel;
import com.app.bustracking.model.BusRouteModel;
import com.app.bustracking.model.FeeStructureModel;
import com.app.bustracking.repository.AcademicYearRepository;
import com.app.bustracking.repository.BusRouteRepository;
import com.app.bustracking.repository.FeeStructureRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FeeStructureService {

    private final FeeStructureRepository feeStructureRepository;
    private final BusRouteRepository routeRepository;
    private final AcademicYearRepository academicYearRepository;

    public FeeStructureService(FeeStructureRepository feeStructureRepository,
                               BusRouteRepository routeRepository,
                               AcademicYearRepository academicYearRepository) {
        this.feeStructureRepository = feeStructureRepository;
        this.routeRepository = routeRepository;
        this.academicYearRepository = academicYearRepository;
    }

    @Transactional
    public FeeStructureResponseDTO create(FeeStructureRequestDTO dto) {
        // Validate that at least one amount is provided
        if (dto.getAmountPerMonth() == null && dto.getAmountPerYear() == null && dto.getAmountPerKm() == null) {
            throw new IllegalArgumentException("At least one fee amount (per month, per year, or per km) must be provided.");
        }

        BusRouteModel route = routeRepository.findById(dto.getRouteId())
                .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
        AcademicYearModel academicYear = academicYearRepository.findById(dto.getAcademicYearId())
                .orElseThrow(() -> new ResourceNotFoundException("Academic Year not found"));

        // Check for duplicate (same route + year)
        feeStructureRepository.findByRouteIdAndAcademicYearYearName(route.getId(), academicYear.getYearName())
                .ifPresent(existing -> {
                    throw new RuntimeException("Fee structure already exists for this route and year.");
                });

        FeeStructureModel entity = FeeStructureMapper.toEntity(dto);
        entity.setRoute(route);
        entity.setAcademicYear(academicYear);
        FeeStructureModel saved = feeStructureRepository.save(entity);
        return FeeStructureMapper.toDTO(saved);
    }

    public List<FeeStructureResponseDTO> getAll() {
        return feeStructureRepository.findAllWithDetails().stream()
                .map(FeeStructureMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FeeStructureResponseDTO getById(Long id) {
        return feeStructureRepository.findByIdWithDetails(id)
                .map(FeeStructureMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Fee structure not found"));
    }

    @Transactional
    public FeeStructureResponseDTO update(Long id, FeeStructureRequestDTO dto) {
        FeeStructureModel entity = feeStructureRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new ResourceNotFoundException("Fee structure not found"));

        // Update only the fields that are provided
        if (dto.getAmountPerMonth() != null) {
            entity.setAmountPerMonth(dto.getAmountPerMonth());
        }
        if (dto.getAmountPerYear() != null) {
            entity.setAmountPerYear(dto.getAmountPerYear());
        }
        if (dto.getAmountPerKm() != null) {
            entity.setAmountPerKm(dto.getAmountPerKm());
        }
        if (dto.getDueDate() != null) {
            entity.setDueDate(dto.getDueDate());
        }
        // Route and Academic Year can be updated if provided
        if (dto.getRouteId() != null) {
            BusRouteModel route = routeRepository.findById(dto.getRouteId())
                    .orElseThrow(() -> new ResourceNotFoundException("Route not found"));
            entity.setRoute(route);
        }
        if (dto.getAcademicYearId() != null) {
            AcademicYearModel academicYear = academicYearRepository.findById(dto.getAcademicYearId())
                    .orElseThrow(() -> new ResourceNotFoundException("Academic Year not found"));
            entity.setAcademicYear(academicYear);
        }

        FeeStructureModel updated = feeStructureRepository.save(entity);
        return FeeStructureMapper.toDTO(updated);
    }

    @Transactional
    public void delete(Long id) {
        feeStructureRepository.deleteById(id);
    }

    public List<FeeStructureResponseDTO> getByAcademicYear(String yearName) {
        return feeStructureRepository.findByAcademicYearYearName(yearName).stream()
                .map(FeeStructureMapper::toDTO)
                .collect(Collectors.toList());
    }

    public FeeStructureResponseDTO getByRouteAndYear(Long routeId, String yearName) {
        return feeStructureRepository.findByRouteIdAndAcademicYearYearName(routeId, yearName)
                .map(FeeStructureMapper::toDTO)
                .orElseThrow(() -> new ResourceNotFoundException("Fee structure not found for this route and year"));
    }
}