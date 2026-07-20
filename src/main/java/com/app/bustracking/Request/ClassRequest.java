package com.app.bustracking.Request;

import jakarta.validation.constraints.NotBlank;

public record ClassRequest(
        @NotBlank String name
) {}