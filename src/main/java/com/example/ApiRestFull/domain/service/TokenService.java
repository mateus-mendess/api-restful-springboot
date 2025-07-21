package com.example.ApiRestFull.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.ApiRestFull.exception.InvalidTokenException;
import com.example.ApiRestFull.exception.TokenGenerationException;
import com.example.ApiRestFull.security.UserSecurity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(UserSecurity userSecurity) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            String token = JWT.create()
                    .withIssuer("api-rest-full")
                    .withSubject(userSecurity.getUsername())
                    .withExpiresAt(Date.from(calculateExpirationTime()))
                    .sign(algorithm);

            return token;
        } catch (JWTCreationException exception) {
            throw new TokenGenerationException("Error while generating token");
        }
    }

    public String validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer("api-rest-full")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception) {
            throw new InvalidTokenException("Error with token validation");
        }
    }

    private Instant calculateExpirationTime() {
        return LocalDateTime.now().plusHours(2).atZone(ZoneOffset.of("-03:00")).toInstant();
    }
}
