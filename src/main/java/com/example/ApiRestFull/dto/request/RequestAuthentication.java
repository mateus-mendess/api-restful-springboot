package com.example.ApiRestFull.dto.request;

import jakarta.validation.constraints.NotBlank;

public record RequestAuthentication(
        @NotBlank(message = "Invalid email or password.")
        String email,

        @NotBlank(message = "Invalid email or password.")
        String password) {
}
