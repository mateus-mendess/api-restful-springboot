package com.example.ApiRestFull.domain.repository;

import com.example.ApiRestFull.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Long> {
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}
