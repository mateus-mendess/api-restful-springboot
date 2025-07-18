package com.example.ApiRestFull.controller;

import com.example.ApiRestFull.domain.service.AuthenticationService;
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

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid RequestAuthentication requestAuthentication) {
        authenticationService.authenticationUser(requestAuthentication);
        return ResponseEntity.ok().build();
    }
}
