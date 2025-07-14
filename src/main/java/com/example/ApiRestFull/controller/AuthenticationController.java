package com.example.ApiRestFull.controller;

import com.example.ApiRestFull.dto.request.RequestAuthentication;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid RequestAuthentication requestAuthentication) {
        return ResponseEntity.ok().build();
    }
}
