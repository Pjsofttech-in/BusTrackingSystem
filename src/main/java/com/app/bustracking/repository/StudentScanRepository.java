package com.app.bustracking.repository;

import com.app.bustracking.Model.StudentScanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentScanRepository extends JpaRepository<StudentScanModel, Long> {
    List<StudentScanModel> findByStudentIdOrderByScannedAtDesc(Long studentId);
    List<StudentScanModel> findByBusIdOrderByScannedAtDesc(Long busId);
}