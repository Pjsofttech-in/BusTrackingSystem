package com.app.bustracking.repository;

import com.app.bustracking.model.BusRouteStopModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusRouteStopRepository extends JpaRepository<BusRouteStopModel, Long> {
    List<BusRouteStopModel> findByRouteIdOrderBySequenceAsc(Long routeId);
}