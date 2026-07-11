package com.bus.tracking.bus_tracking_system.mapper;

import com.bus.tracking.bus_tracking_system.dto.StudentScanResponseDTO;
import com.bus.tracking.bus_tracking_system.model.StudentScan;

public class StudentScanMapper {

    public static StudentScanResponseDTO toDTO(StudentScan scan) {

        StudentScanResponseDTO dto = new StudentScanResponseDTO();

        dto.setId(scan.getId());
        dto.setStudentId(scan.getStudent().getId());
        dto.setStudentName(scan.getStudent().getName());
        dto.setBusId(scan.getBus().getId());
//        dto.setBusNumber(scan.getBus().getBusNumber());
        dto.setScannedAt(scan.getScannedAt());

        return dto;
    }
}