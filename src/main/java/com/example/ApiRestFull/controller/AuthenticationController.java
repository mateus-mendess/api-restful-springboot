package com.example.ApiRestFull.controller;

import com.example.ApiRestFull.domain.entity.User;
import com.example.ApiRestFull.domain.service.AuthenticationService;
import com.example.ApiRestFull.domain.service.TokenService;
import com.example.ApiRestFull.dto.request.RequestAuthentication;
import com.example.ApiRestFull.dto.response.ResponseLogin;
import com.example.ApiRestFull.security.UserSecurity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final TokenService tokenService;

    public AuthenticationController(AuthenticationService authenticationService, TokenService tokenService) {
        this.authenticationService = authenticationService;
        this.tokenService = tokenService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid RequestAuthentication requestAuthentication) {
        Authentication authentication = authenticationService.authenticationUser(requestAuthentication);
        String token = tokenService.generateToken((UserSecurity) authentication.getPrincipal());
        ResponseLogin responseLogin = new ResponseLogin(token);
        return ResponseEntity.ok().body(responseLogin);
    }
}
