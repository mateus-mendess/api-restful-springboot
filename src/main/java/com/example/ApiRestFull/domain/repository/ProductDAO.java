package com.example.ApiRestFull.domain.repository;

import com.example.ApiRestFull.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDAO extends JpaRepository<Product, Long> {
}
