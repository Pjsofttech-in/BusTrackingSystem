package com.app.bustracking.repository;

import com.app.bustracking.Model.BusLocationModel;
import com.app.bustracking.Model.BusModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BusLocationRepository extends JpaRepository<BusLocationModel, Long> {

    Optional<BusLocationModel> findTopByBusOrderByTimestampDesc(BusModel bus);
    List<BusLocationModel> findByBusOrderByTimestampDesc(BusModel bus);
    List<BusLocationModel> findByBusAndTimestampAfterOrderByTimestampAsc(BusModel bus, LocalDateTime from);
}