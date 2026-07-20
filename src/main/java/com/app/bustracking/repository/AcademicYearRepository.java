package com.app.bustracking.repository;

import com.app.bustracking.Model.AcademicYearModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AcademicYearRepository extends JpaRepository<AcademicYearModel, Long> {
}