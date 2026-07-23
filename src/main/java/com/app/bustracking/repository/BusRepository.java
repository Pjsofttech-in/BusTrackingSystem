package com.app.bustracking.repository;

import com.app.bustracking.model.BusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRepository extends JpaRepository<BusModel, Long> {

    // ✅ Fetch all buses with their service providers
    @Query("SELECT b FROM BusModel b JOIN FETCH b.serviceProvider")
    List<BusModel> findAllWithProvider();

    // ✅ Fetch a single bus with its service provider
    @Query("SELECT b FROM BusModel b JOIN FETCH b.serviceProvider WHERE b.id = :id")
    Optional<BusModel> findByIdWithProvider(@Param("id") Long id);
}