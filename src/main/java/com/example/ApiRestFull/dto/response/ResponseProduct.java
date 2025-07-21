package com.example.ApiRestFull.dto.response;

public record ResponseProduct(
         Long id,
         String name,
         String description,
         Double price,
         Integer quantity,
         String category,
         Boolean active
) {}