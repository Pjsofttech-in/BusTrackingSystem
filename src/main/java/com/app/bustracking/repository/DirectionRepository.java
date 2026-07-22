package com.app.bustracking.repository;

import com.app.bustracking.model.DirectionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DirectionRepository extends JpaRepository<DirectionModel, Long> {

    Optional<DirectionModel> findByName(String name);
    Optional<DirectionModel> findByAbbreviation(String abbreviation);

    // Query to find a direction by heading degree (range)
    @Query("SELECT d FROM DirectionModel d WHERE :heading BETWEEN d.minDegrees AND d.maxDegrees")
    Optional<DirectionModel> findByHeading(@Param("heading") Double heading);
}