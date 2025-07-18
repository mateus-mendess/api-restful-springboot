package com.example.ApiRestFull.domain.enums;

import lombok.Getter;

@Getter
public enum UserRoles {
    ADMIN("admin"),
    USER("user"),
    MODERATOR("moderator");

    String role;

    UserRoles(String role) {
        this.role = role;
    }
}
