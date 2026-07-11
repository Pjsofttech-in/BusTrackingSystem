package com.bus.tracking.bus_tracking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.bus.tracking.bus_tracking_system.model.Driver;

import java.util.List;
import java.util.Optional;

public interface DriverRepository extends JpaRepository<Driver, Long> {

    Driver findByPhone(String phone);

    Optional<Driver> findByEmail(String email);

    List<Driver> findByStatus(String status);

    List<Driver> findByCity(String city);

    @Query("SELECT d FROM Driver d WHERE d.licenseExpiryDate < CURRENT_DATE")
    List<Driver> findExpiredLicenses();

    @Query("SELECT d FROM Driver d WHERE d.licenseExpiryDate BETWEEN :startDate AND :endDate")
    List<Driver> findLicensesExpiringBetween(@Param("startDate") java.time.LocalDate startDate,
                                             @Param("endDate") java.time.LocalDate endDate);
}