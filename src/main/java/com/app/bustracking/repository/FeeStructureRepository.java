package com.app.bustracking.repository;

import com.app.bustracking.model.FeeStructureModel;   // ✅ import the entity
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FeeStructureRepository extends JpaRepository<FeeStructureModel, Long> {

    @Query("SELECT fs FROM FeeStructureModel fs JOIN FETCH fs.route JOIN FETCH fs.academicYear")
    List<FeeStructureModel> findAllWithDetails();

    @Query("SELECT fs FROM FeeStructureModel fs JOIN FETCH fs.route JOIN FETCH fs.academicYear WHERE fs.id = :id")
    Optional<FeeStructureModel> findByIdWithDetails(@Param("id") Long id);

    @Query("SELECT fs FROM FeeStructureModel fs JOIN FETCH fs.route JOIN FETCH fs.academicYear WHERE fs.academicYear.yearName = :yearName")
    List<FeeStructureModel> findByAcademicYearYearName(@Param("yearName") String yearName);

    @Query("SELECT fs FROM FeeStructureModel fs JOIN FETCH fs.route JOIN FETCH fs.academicYear WHERE fs.route.id = :routeId AND fs.academicYear.yearName = :yearName")
    Optional<FeeStructureModel> findByRouteIdAndAcademicYearYearName(@Param("routeId") Long routeId, @Param("yearName") String yearName);
}