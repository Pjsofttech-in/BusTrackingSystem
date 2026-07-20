// src/main/java/com/app/bustracking/repository/BusStopRepository.java
package com.app.bustracking.repository;

import com.app.bustracking.Model.BusStopModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BusStopRepository extends JpaRepository<BusStopModel, Long> {
}