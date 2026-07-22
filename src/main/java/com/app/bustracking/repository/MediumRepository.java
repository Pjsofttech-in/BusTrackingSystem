package com.app.bustracking.repository;

import com.app.bustracking.model.MediumModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediumRepository extends JpaRepository<MediumModel, Long> {
}