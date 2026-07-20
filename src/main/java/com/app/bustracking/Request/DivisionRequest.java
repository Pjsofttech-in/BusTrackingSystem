package com.app.bustracking.Request;

import jakarta.validation.constraints.NotBlank;

public record DivisionRequest(
        @NotBlank String divisionName
) {}