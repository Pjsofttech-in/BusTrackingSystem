package com.app.bustracking.repository;

import com.app.bustracking.Model.BusTripModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BusTripRepository extends JpaRepository<BusTripModel, Long> {
    List<BusTripModel> findByTripStatus(String status);
}