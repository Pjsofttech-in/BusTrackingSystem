package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentScanRequestDTO {
    private Long studentId;
    private Long busId;
}