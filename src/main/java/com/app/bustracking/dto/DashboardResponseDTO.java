package com.app.bustracking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardResponseDTO {
    private String academicYear;
    private List<Map<String, Object>> buses;
    private List<Map<String, Object>> busLocations;
    private List<String> busStops;
    private List<String> conductors;
    private List<String> drivers;
    private List<String> divisions;
    private List<String> mediums;
    private List<Map<String, Object>> routes;
    private List<Map<String, Object>> routeStops;
    private List<String> serviceProviders;
    private List<Map<String, Object>> students;
    private List<Map<String, Object>> studentFeePayments;
    private List<Map<String, Object>> studentScans;
    private Map<String, Object> stats;

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public List<Map<String, Object>> getBuses() {
        return buses;
    }

    public void setBuses(List<Map<String, Object>> buses) {
        this.buses = buses;
    }

    public List<Map<String, Object>> getBusLocations() {
        return busLocations;
    }

    public void setBusLocations(List<Map<String, Object>> busLocations) {
        this.busLocations = busLocations;
    }

    public List<String> getBusStops() {
        return busStops;
    }

    public void setBusStops(List<String> busStops) {
        this.busStops = busStops;
    }

    public List<String> getConductors() {
        return conductors;
    }

    public void setConductors(List<String> conductors) {
        this.conductors = conductors;
    }

    public List<String> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<String> drivers) {
        this.drivers = drivers;
    }

    public List<String> getDivisions() {
        return divisions;
    }

    public void setDivisions(List<String> divisions) {
        this.divisions = divisions;
    }

    public List<String> getMediums() {
        return mediums;
    }

    public void setMediums(List<String> mediums) {
        this.mediums = mediums;
    }

    public List<Map<String, Object>> getRoutes() {
        return routes;
    }

    public void setRoutes(List<Map<String, Object>> routes) {
        this.routes = routes;
    }

    public List<Map<String, Object>> getRouteStops() {
        return routeStops;
    }

    public void setRouteStops(List<Map<String, Object>> routeStops) {
        this.routeStops = routeStops;
    }

    public List<String> getServiceProviders() {
        return serviceProviders;
    }

    public void setServiceProviders(List<String> serviceProviders) {
        this.serviceProviders = serviceProviders;
    }

    public List<Map<String, Object>> getStudents() {
        return students;
    }

    public void setStudents(List<Map<String, Object>> students) {
        this.students = students;
    }

    public List<Map<String, Object>> getStudentFeePayments() {
        return studentFeePayments;
    }

    public void setStudentFeePayments(List<Map<String, Object>> studentFeePayments) {
        this.studentFeePayments = studentFeePayments;
    }

    public List<Map<String, Object>> getStudentScans() {
        return studentScans;
    }

    public void setStudentScans(List<Map<String, Object>> studentScans) {
        this.studentScans = studentScans;
    }

    public Map<String, Object> getStats() {
        return stats;
    }

    public void setStats(Map<String, Object> stats) {
        this.stats = stats;
    }
}