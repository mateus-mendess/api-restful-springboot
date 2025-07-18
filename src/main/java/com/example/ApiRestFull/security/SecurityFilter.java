package com.example.ApiRestFull.security;

import com.example.ApiRestFull.domain.entity.User;
import com.example.ApiRestFull.domain.repository.UserDAO;
import com.example.ApiRestFull.domain.service.TokenService;
import com.example.ApiRestFull.exception.NotFoundException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenService tokenService;
    private final UserDAO userDAO;

    public SecurityFilter(TokenService tokenService, UserDAO userDAO) {
        this.tokenService = tokenService;
        this.userDAO = userDAO;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = recoverToken(request);
        if (token != null) {
            String email = this.tokenService.validateToken(token);
            UserSecurity userSecurity = new UserSecurity(userDAO.findByEmail(email).orElseThrow(() ->
                    new NotFoundException("User not found!")));

            Authentication authentication = new UsernamePasswordAuthenticationToken(userSecurity, null, userSecurity.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(request, response);
    }

    private String recoverToken(HttpServletRequest httpServletRequest) {
        String authorization = httpServletRequest.getHeader("Authorization");

        if (authorization == null) return null;

        return authorization.replace("Bearer ", "");
    }
}
