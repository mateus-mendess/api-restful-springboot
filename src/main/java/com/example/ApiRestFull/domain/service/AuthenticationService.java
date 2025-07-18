package com.example.ApiRestFull.domain.service;

import com.example.ApiRestFull.dto.request.RequestAuthentication;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    public Authentication authenticationUser(RequestAuthentication requestAuthentication) {
        Authentication usernamePassword = new UsernamePasswordAuthenticationToken(requestAuthentication.email(), requestAuthentication.password());
        return authenticationManager.authenticate(usernamePassword);
    }
}
