package com.app.bustracking.Request;

import jakarta.validation.constraints.NotBlank;

public record AcademicYearRequest(
        @NotBlank String yearName
) {}