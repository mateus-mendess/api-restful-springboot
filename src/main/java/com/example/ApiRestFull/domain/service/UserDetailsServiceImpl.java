package com.example.ApiRestFull.domain.service;

import com.example.ApiRestFull.domain.entity.User;
import com.example.ApiRestFull.domain.repository.UserDAO;
import com.example.ApiRestFull.security.UserSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAO userDAO;

    public UserDetailsServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        return new UserSecurity(userDAO.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("User not found")));
    }
}
