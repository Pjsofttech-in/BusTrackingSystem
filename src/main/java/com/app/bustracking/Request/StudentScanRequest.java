package com.app.bustracking.Request;

import jakarta.validation.constraints.NotNull;

public record StudentScanRequest(
        @NotNull Long studentId,
        @NotNull Long busId
) {}