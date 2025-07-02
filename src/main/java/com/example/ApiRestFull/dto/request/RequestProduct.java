package com.example.ApiRestFull.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record RequestProduct(
        Long id,

        @NotBlank
        @Pattern(regexp = "^.{1,75}$", message = "O nome do produto é obrigatório e deve conter entre 1 e 75 caracteres.")
        String name,

        String description,

        @NotNull
        @Pattern(regexp = "^(?!0\\.00)\\d{1,8}(\\.\\d{2})$", message = "O preço do produto é obrigatório, deve ser maior que 0 e deve ter duas casas decimais (por exemplo, 10.00).")
        String price,

        Integer quantity,

        String category,

        @NotBlank
        @Pattern(regexp = "^(true|false)$", message = "O status do produto deve ser 'true' ou 'false'.")
        String active
) {}
