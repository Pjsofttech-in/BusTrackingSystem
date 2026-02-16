package com.bus.tracking.bus_tracking_system.repository;

import com.bus.tracking.bus_tracking_system.model.StudentFeePayment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentFeePaymentRepository
        extends JpaRepository<StudentFeePayment, Long> {

    List<StudentFeePayment> findByStudentId(Long studentId);
}
