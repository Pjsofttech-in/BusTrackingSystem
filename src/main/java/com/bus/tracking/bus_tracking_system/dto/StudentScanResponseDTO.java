package com.bus.tracking.bus_tracking_system.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class StudentScanResponseDTO {

    private Long id;
    private Long studentId;
    private String studentName;
    private Long busId;
    private String busNumber;
    private LocalDateTime scannedAt;


}