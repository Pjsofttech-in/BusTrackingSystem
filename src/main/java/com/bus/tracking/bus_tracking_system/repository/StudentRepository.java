package com.bus.tracking.bus_tracking_system.repository;

import com.bus.tracking.bus_tracking_system.model.Student;
import com.bus.tracking.bus_tracking_system.dto.StudentAcademicInfoDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Get by ID
    @Query("SELECT new com.bus.tracking.bus_tracking_system.dto.StudentAcademicInfoDTO(" +
            "s.studentClass, s.division, s.medium, s.academicYear) " +
            "FROM Student s WHERE s.id = :id")
    StudentAcademicInfoDTO getAcademicInfo(@Param("id") Long id);

    // Get ALL
    @Query("SELECT new com.bus.tracking.bus_tracking_system.dto.StudentAcademicInfoDTO(" +
            "s.studentClass, s.division, s.medium, s.academicYear) " +
            "FROM Student s")
    List<StudentAcademicInfoDTO> getAllAcademicInfo();
}