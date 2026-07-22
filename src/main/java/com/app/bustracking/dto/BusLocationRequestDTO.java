package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Getter
@Setter
public class BusLocationRequestDTO {
    private Long busId;
    private Double latitude;
    private Double longitude;
    private Double speed;
    private Double heading;
    private Long directionId;      // relation
    private Double accuracy;
    private String status;
    private LocalDateTime timestamp;  // optional
}