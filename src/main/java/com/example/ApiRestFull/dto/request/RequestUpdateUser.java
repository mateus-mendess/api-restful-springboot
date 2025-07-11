package com.example.ApiRestFull.dto.request;


import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestUpdateUser{
        @Size(min = 5, max = 30, message = "Username deve ter entre 3 e 20 caracteres")
        @Pattern(regexp = "^[a-zA-Z0-9._]+$",
                message = "Username pode conter apenas letras, números, ponto e underline")
        private String username;

        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres")
        @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&]).+$",
                message = "A senha deve conter pelo menos uma letra, um número e um caractere especial")
        private String password;
}
