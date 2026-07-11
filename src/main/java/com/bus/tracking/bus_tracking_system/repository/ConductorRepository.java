package com.bus.tracking.bus_tracking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.bus.tracking.bus_tracking_system.model.Conductor;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ConductorRepository extends JpaRepository<Conductor, Long> {

    Optional<Conductor> findByEmail(String email);

    List<Conductor> findByStatus(String status);

    List<Conductor> findByCity(String city);

    @Query("SELECT c FROM Conductor c WHERE c.licenseExpiryDate IS NOT NULL AND c.licenseExpiryDate < :today")
    List<Conductor> findExpiredLicenses(@Param("today") Date today);

    @Query("SELECT c FROM Conductor c WHERE c.licenseExpiryDate IS NOT NULL AND c.licenseExpiryDate BETWEEN :startDate AND :endDate")
    List<Conductor> findLicensesExpiringBetween(@Param("startDate") Date startDate,
                                                @Param("endDate") Date endDate);

    @Query("SELECT c FROM Conductor c WHERE LOWER(c.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Conductor> findByNameContaining(@Param("name") String name);

    @Query("SELECT c FROM Conductor c WHERE c.phone = :phone")
    Optional<Conductor> findByPhone(@Param("phone") String phone);
}