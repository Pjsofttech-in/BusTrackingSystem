package com.app.bustracking.repository;

import com.app.bustracking.model.DivisionModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DivisionRepository extends JpaRepository<DivisionModel, Long> {
}