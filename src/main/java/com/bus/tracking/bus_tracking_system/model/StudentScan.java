package com.bus.tracking.bus_tracking_system.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class StudentScan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "bus_id")
    private Bus bus;

    private LocalDateTime scannedAt;

    @PrePersist
    public void onCreate() {
        scannedAt = LocalDateTime.now();
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Student getStudent() {return student;}
    public void setStudent(Student student) {this.student = student;}
    public Bus getBus() {return bus;}
    public void setBus(Bus bus) {this.bus = bus;}
    public LocalDateTime getScannedAt() { return scannedAt; }
    public void setScannedAt(LocalDateTime scannedAt) { this.scannedAt = scannedAt; }
}
