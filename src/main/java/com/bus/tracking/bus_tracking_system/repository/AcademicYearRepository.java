package com.bus.tracking.bus_tracking_system.repository;

import com.bus.tracking.bus_tracking_system.model.AcademicYear;
import org.springframework.data.jpa.repository.JpaRepository;

    public interface AcademicYearRepository extends JpaRepository<AcademicYear, Long> {
    }

