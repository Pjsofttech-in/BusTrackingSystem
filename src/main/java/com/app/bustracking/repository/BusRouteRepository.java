package com.app.bustracking.repository;

import com.app.bustracking.model.BusRouteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRouteRepository extends JpaRepository<BusRouteModel, Long> {
}