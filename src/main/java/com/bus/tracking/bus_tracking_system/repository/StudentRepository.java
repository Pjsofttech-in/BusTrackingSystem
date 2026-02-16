package com.bus.tracking.bus_tracking_system.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.bus.tracking.bus_tracking_system.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
