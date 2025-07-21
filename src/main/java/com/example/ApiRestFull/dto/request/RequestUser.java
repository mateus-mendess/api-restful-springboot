package com.example.ApiRestFull.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUser {
        @NotBlank
        @Size(min = 5, max = 30, message = "Username deve ter entre 3 e 20 caracteres")
        @Pattern(regexp = "^[a-zA-Z0-9._]+$",
                message = "Username pode conter apenas letras, números, ponto e underline")
        private String username;

        @NotBlank
        @Email(message = "Email inválido")
        private String email;

        @NotBlank
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&]).+$",
                message = "A senha deve conter pelo menos uma letra, um número e um caractere especial")
        private String password;

        @NotBlank
        @Size(min = 5, max = 100, message = "Nome completo deve ter entre 5 e 100 caracteres")
        @Pattern(regexp = "^[A-Za-zÀ-ÿ]+(?:\\s[A-Za-zÀ-ÿ]+)+$",
                message = "Nome completo deve conter pelo menos nome e sobrenome, sem números")
        private String fullName;

        @NotBlank
        private String roles;
}
