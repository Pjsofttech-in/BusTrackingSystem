package com.app.bustracking.service.impl;

import com.app.bustracking.Model.*;
import com.app.bustracking.Response.DashboardResponse;
import com.app.bustracking.repository.*;
import com.app.bustracking.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final StudentRepository studentRepository;
    private final BusRepository busRepository;
    private final DriverRepository driverRepository;
    private final ConductorRepository conductorRepository;
    private final BusStopRepository busStopRepository;
    private final BusRouteRepository busRouteRepository;
    private final BusLocationRepository busLocationRepository;
    private final StudentFeePaymentRepository feePaymentRepository;
    private final StudentScanRepository scanRepository;
    private final ServiceProviderRepository serviceProviderRepository;
    private final DivisionRepository divisionRepository;
    private final MediumRepository mediumRepository;
    private final AcademicYearRepository academicYearRepository;

    @Override
    public DashboardResponse getDashboardData() {
        // Academic Year (latest)
        String academicYear = academicYearRepository.findAll()
                .stream().findFirst().map(AcademicYearModel::getYearName).orElse("2025-2026");

        // Stats
        Map<String, Object> stats = new HashMap<>();
        stats.put("activeBuses", busRepository.findAll().stream().filter(b -> "ACTIVE".equals(b.getStatus())).count());
        stats.put("totalStudents", studentRepository.count());
        stats.put("onTimeRate", 94); // placeholder – you can calculate from trips
        stats.put("totalTrips", 28); // placeholder

        // Buses – map to simple DTO
        List<Map<String, Object>> buses = busRepository.findAll().stream()
                .map(b -> Map.<String, Object>of(
                        "id", b.getId(),
                        "name", "Bus #" + b.getId(),
                        "route", b.getBusModelName() != null ? b.getBusModelName() : "N/A",
                        "students", 0, // you can calculate if needed
                        "stops", 0,
                        "status", b.getStatus() != null ? b.getStatus().toLowerCase() : "unknown",
                        "eta", "8 min",
                        "supplier", b.getServiceProvider() != null ? b.getServiceProvider().getServiceprovidername() : "N/A",
                        "trip", "AM-01"
                ))
                .collect(Collectors.toList());

        // Bus Locations – simplified
        List<Map<String, Object>> busLocations = busLocationRepository.findAll().stream()
                .limit(5)
                .map(loc -> Map.<String, Object>of(
                        "busId", loc.getBus().getId(),
                        "lat", loc.getLatitude(),
                        "lng", loc.getLongitude(),
                        "lastUpdate", loc.getTimestamp() != null ? loc.getTimestamp().toString() : "N/A"
                ))
                .collect(Collectors.toList());

        // Bus Stops – names only
        List<String> busStops = busStopRepository.findAll().stream()
                .map(BusStopModel::getStopName)
                .collect(Collectors.toList());

        // Conductors – names only
        List<String> conductors = conductorRepository.findAll().stream()
                .map(ConductorModel::getName)
                .collect(Collectors.toList());

        // Drivers – names only
        List<String> drivers = driverRepository.findAll().stream()
                .map(DriverModel::getName)
                .collect(Collectors.toList());

        // Divisions – names only
        List<String> divisions = divisionRepository.findAll().stream()
                .map(DivisionModel::getDivisionName)
                .collect(Collectors.toList());

        // Mediums – names only
        List<String> mediums = mediumRepository.findAll().stream()
                .map(MediumModel::getMediumName)
                .collect(Collectors.toList());

        // Routes – simplified
        List<Map<String, Object>> routes = busRouteRepository.findAll().stream()
                .map(r -> Map.<String, Object>of(
                        "id", r.getId(),
                        "name", r.getRouteName(),
                        "stops", r.getStops().size(),
                        "students", 0
                ))
                .collect(Collectors.toList());

        // Route Stops – simple mapping
        List<Map<String, Object>> routeStops = busRouteRepository.findAll().stream()
                .flatMap(r -> r.getStops().stream()
                        .map(rs -> Map.<String, Object>of(
                                "route", r.getRouteName(),
                                "stop", rs.getStop().getStopName(),
                                "order", rs.getSequence()
                        ))
                )
                .collect(Collectors.toList());

        // Service Providers – names only
        List<String> serviceProviders = serviceProviderRepository.findAll().stream()
                .map(ServiceProviderModel::getServiceprovidername)
                .collect(Collectors.toList());

        // Students – simplified with fee and scan status
        List<Map<String, Object>> students = studentRepository.findAll().stream()
                .map(s -> Map.<String, Object>of(
                        "id", s.getId(),
                        "name", s.getName(),
                        "bus", s.getInBus() ? "Yes" : "No", // placeholder
                        "feePaid", false, // can be calculated from payments
                        "scan", "N/A"
                ))
                .collect(Collectors.toList());

        // Student Fee Payments – recent 3
        List<Map<String, Object>> studentFeePayments = feePaymentRepository.findAll().stream()
                .sorted((a, b) -> b.getPaymentDateTime().compareTo(a.getPaymentDateTime()))
                .limit(3)
                .map(p -> Map.<String, Object>of(
                        "student", p.getStudent().getName(),
                        "amount", p.getAmount(),
                        "date", p.getPaymentDate().toString(),
                        "status", p.getStatus()
                ))
                .collect(Collectors.toList());

        // Student Scans – recent 3
        List<Map<String, Object>> studentScans = scanRepository.findAll().stream()
                .sorted((a, b) -> b.getScannedAt().compareTo(a.getScannedAt()))
                .limit(3)
                .map(scan -> Map.<String, Object>of(
                        "student", scan.getStudent().getName(),
                        "bus", scan.getBus().getBusNumber(),
                        "time", scan.getScannedAt().toString(),
                        "type", "Board"
                ))
                .collect(Collectors.toList());

        return new DashboardResponse(
                academicYear,
                buses,
                busLocations,
                busStops,
                conductors,
                drivers,
                divisions,
                mediums,
                routes,
                routeStops,
                serviceProviders,
                students,
                studentFeePayments,
                studentScans,
                stats
        );
    }
}