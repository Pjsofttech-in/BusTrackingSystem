package com.app.bustracking.repository;

import com.app.bustracking.model.StudentScanModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentScanRepository extends JpaRepository<StudentScanModel, Long> {
    // ✅ Returns entity type, not Object[]
    List<StudentScanModel> findByStudentId(Long studentId);
    List<StudentScanModel> findByBusId(Long busId);
}