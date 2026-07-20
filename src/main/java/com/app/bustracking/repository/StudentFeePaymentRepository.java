package com.app.bustracking.repository;

import com.app.bustracking.Model.StudentFeePaymentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentFeePaymentRepository extends JpaRepository<StudentFeePaymentModel, Long> {
    List<StudentFeePaymentModel> findByStudentIdOrderByPaymentDateTimeDesc(Long studentId);
    List<StudentFeePaymentModel> findByStudentId(Long studentId);
}