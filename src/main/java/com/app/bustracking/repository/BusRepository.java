package com.app.bustracking.repository;

import com.app.bustracking.Model.BusModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BusRepository extends JpaRepository<BusModel, Long> {
}