package com.example.ApiRestFull.dto.response;

public record ResponseUser(
        Long id,
        String username,
        String email,
        String fullName
) {}
