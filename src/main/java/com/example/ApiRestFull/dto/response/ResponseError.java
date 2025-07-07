package com.example.ApiRestFull.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseError {
    private String code;
    private String status;
    private String message;

    public ResponseError(String code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }
}
