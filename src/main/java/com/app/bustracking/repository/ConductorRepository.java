// src/main/java/com/app/bustracking/repository/ConductorRepository.java
package com.app.bustracking.repository;

import com.app.bustracking.Model.ConductorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConductorRepository extends JpaRepository<ConductorModel, Long> {
}