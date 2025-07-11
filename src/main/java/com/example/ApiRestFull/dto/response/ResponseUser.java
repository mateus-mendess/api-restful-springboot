package com.example.ApiRestFull.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseUser {
    private Long id;
    private String username;
    private String email;
    private String fullName;
}
