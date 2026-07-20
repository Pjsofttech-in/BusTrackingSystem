package com.app.bustracking.repository;

import com.app.bustracking.Model.MediumModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediumRepository extends JpaRepository<MediumModel, Long> {
}