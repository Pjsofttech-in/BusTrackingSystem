package com.app.bustracking.repository;

import com.app.bustracking.model.BusLocationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusLocationRepository extends JpaRepository<BusLocationModel, Long> {

    // ✅ Returns the latest location for a given bus (by timestamp descending)
    Optional<BusLocationModel> findTopByBusIdOrderByTimestampDesc(Long busId);

    // ✅ Returns all locations for a bus, ordered by timestamp descending
    List<BusLocationModel> findByBusIdOrderByTimestampDesc(Long busId);
}