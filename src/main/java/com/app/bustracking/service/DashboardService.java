package com.app.bustracking.service;

import com.app.bustracking.dto.DashboardResponseDTO;
import com.app.bustracking.mapper.*;
import com.app.bustracking.model.*;
import com.app.bustracking.repository.*;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    private final BusRepository busRepository;
    private final BusLocationRepository busLocationRepository;
    private final BusStopRepository busStopRepository;
    private final ConductorRepository conductorRepository;
    private final DriverRepository driverRepository;
    private final DivisionRepository divisionRepository;
    private final MediumRepository mediumRepository;
    private final BusRouteRepository routeRepository;
    private final ServiceProviderRepository serviceProviderRepository;
    private final StudentRepository studentRepository;
    private final StudentFeePaymentRepository studentFeePaymentRepository;
    private final StudentScanRepository studentScanRepository;

    public DashboardService(BusRepository busRepository,
                            BusLocationRepository busLocationRepository,
                            BusStopRepository busStopRepository,
                            ConductorRepository conductorRepository,
                            DriverRepository driverRepository,
                            DivisionRepository divisionRepository,
                            MediumRepository mediumRepository,
                            BusRouteRepository routeRepository,
                            ServiceProviderRepository serviceProviderRepository,
                            StudentRepository studentRepository,
                            StudentFeePaymentRepository studentFeePaymentRepository,
                            StudentScanRepository studentScanRepository) {
        this.busRepository = busRepository;
        this.busLocationRepository = busLocationRepository;
        this.busStopRepository = busStopRepository;
        this.conductorRepository = conductorRepository;
        this.driverRepository = driverRepository;
        this.divisionRepository = divisionRepository;
        this.mediumRepository = mediumRepository;
        this.routeRepository = routeRepository;
        this.serviceProviderRepository = serviceProviderRepository;
        this.studentRepository = studentRepository;
        this.studentFeePaymentRepository = studentFeePaymentRepository;
        this.studentScanRepository = studentScanRepository;
    }

    public DashboardResponseDTO getDashboardData() {
        DashboardResponseDTO dto = new DashboardResponseDTO();

        // Academic year (for now, take first or latest)
        dto.setAcademicYear("2025-2026"); // can be dynamic

        // Buses
        List<Map<String, Object>> buses = busRepository.findAll().stream()
                .map(BusMapper::toDTO)
                .map(b -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", b.getId());
                    map.put("busNumber", b.getBusNumber());
                    map.put("busType", b.getBusType());
                    map.put("status", b.getStatus());
                    return map;
                })
                .collect(Collectors.toList());
        dto.setBuses(buses);

        // Bus Locations (latest per bus)
        List<Map<String, Object>> busLocations = busLocationRepository.findAll().stream()
                .map(BusLocationMapper::toDTO)
                .map(l -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", l.getId());
                    map.put("busId", l.getBus().getId());
                    map.put("latitude", l.getLatitude());
                    map.put("longitude", l.getLongitude());
                    map.put("status", l.getStatus());
                    return map;
                })
                .collect(Collectors.toList());
        dto.setBusLocations(busLocations);

        // Bus Stops (names)
        dto.setBusStops(busStopRepository.findAll().stream()
                .map(BusStopModel::getStopName)
                .collect(Collectors.toList()));

        // Conductors (names)
        dto.setConductors(conductorRepository.findAll().stream()
                .map(ConductorModel::getName)
                .collect(Collectors.toList()));

        // Drivers (names)
        dto.setDrivers(driverRepository.findAll().stream()
                .map(DriverModel::getName)
                .collect(Collectors.toList()));

        // Divisions (names)
        dto.setDivisions(divisionRepository.findAll().stream()
                .map(DivisionModel::getDivisionName)
                .collect(Collectors.toList()));

        // Mediums (names)
        dto.setMediums(mediumRepository.findAll().stream()
                .map(MediumModel::getMediumName)
                .collect(Collectors.toList()));

        // Routes (simplified)
        List<Map<String, Object>> routes = routeRepository.findAll().stream()
                .map(BusRouteMapper::toDTO)
                .map(r -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", r.getId());
                    map.put("routeName", r.getRouteName());
                    map.put("status", r.getStatus());
                    return map;
                })
                .collect(Collectors.toList());
        dto.setRoutes(routes);

        // Route Stops (sample)
        List<Map<String, Object>> routeStops = routeRepository.findAll().stream()
                .flatMap(r -> r.getStops().stream())
                .map(rs -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("routeId", rs.getRoute().getId());
                    map.put("stopName", rs.getStop().getStopName());
                    map.put("sequence", rs.getSequence());
                    return map;
                })
                .collect(Collectors.toList());
        dto.setRouteStops(routeStops);

        // Service Providers (names)
        dto.setServiceProviders(serviceProviderRepository.findAll().stream()
                .map(ServiceProviderModel::getServiceprovidername)
                .collect(Collectors.toList()));

        // Students (simplified)
        List<Map<String, Object>> students = studentRepository.findAll().stream()
                .map(StudentMapper::toDTO)
                .map(s -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", s.getId());
                    map.put("name", s.getName());
                    map.put("rollNumber", s.getRollNumber());
                    map.put("status", s.getStatus());
                    return map;
                })
                .collect(Collectors.toList());
        dto.setStudents(students);

        // Student Fee Payments (simplified)
        List<Map<String, Object>> feePayments = studentFeePaymentRepository.findAll().stream()
                .map(StudentFeePaymentMapper::toDTO)
                .map(f -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", f.getId());
                    map.put("studentName", f.getStudent().getName());
                    map.put("amount", f.getAmount());
                    map.put("status", f.getStatus());
                    return map;
                })
                .collect(Collectors.toList());
        dto.setStudentFeePayments(feePayments);

        // Student Scans (simplified)
        List<Map<String, Object>> scans = studentScanRepository.findAll().stream()
                .map(StudentScanMapper::toDTO)
                .map(s -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("id", s.getId());
                    map.put("studentName", s.getStudent().getName());
                    map.put("busNumber", s.getBus().getBusNumber());
                    map.put("scannedAt", s.getScannedAt());
                    return map;
                })
                .collect(Collectors.toList());
        dto.setStudentScans(scans);

        // Stats
        Map<String, Object> stats = new HashMap<>();
//        stats.put("activeBuses", busRepository.countByStatus("ACTIVE"));
        stats.put("totalStudents", studentRepository.count());
        stats.put("totalTrips", 47); // could be fetched from trip repository
        stats.put("onTimeRate", 92);
        dto.setStats(stats);

        return dto;
    }
}