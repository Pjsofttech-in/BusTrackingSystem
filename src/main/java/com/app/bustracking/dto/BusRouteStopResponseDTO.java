package com.app.bustracking.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusRouteStopResponseDTO {
    private Long id;
    private BusStopResponseDTO stop;
    private Integer sequence;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BusStopResponseDTO getStop() {
        return stop;
    }

    public void setStop(BusStopResponseDTO stop) {
        this.stop = stop;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }
}