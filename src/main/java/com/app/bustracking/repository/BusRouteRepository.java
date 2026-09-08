package com.app.bustracking.repository;

import com.app.bustracking.model.BusRouteModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BusRouteRepository extends JpaRepository<BusRouteModel, Long> {

    @Query("SELECT r FROM BusRouteModel r " +
            "JOIN FETCH r.startStop ss " +
            "JOIN FETCH r.endStop es " +
            "LEFT JOIN FETCH r.stops stops " +
            "LEFT JOIN FETCH stops.stop stop")
    List<BusRouteModel> findAllWithDetails();

    @Query("SELECT r FROM BusRouteModel r " +
            "JOIN FETCH r.startStop ss " +
            "JOIN FETCH r.endStop es " +
            "LEFT JOIN FETCH r.stops stops " +
            "LEFT JOIN FETCH stops.stop stop " +
            "WHERE r.id = :id")
    Optional<BusRouteModel> findByIdWithDetails(@Param("id") Long id);
}