package com.app.bustracking.repository;

import com.app.bustracking.model.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {
    List<StudentModel> findByStatus(String status);
    List<StudentModel> findByStudentClass(String studentClass);
    List<StudentModel> findByDivision(String division);
}