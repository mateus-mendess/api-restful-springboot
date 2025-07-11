package com.example.ApiRestFull.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseError {
    private String code;
    private String status;
    private String message;
    private List<String> errors;

    public ResponseError(String code, String status, String message) {
        this.code = code;
        this.status = status;
        this.message = message;
    }

    public ResponseError(String code, String status, String message, List<String> errors) {
        this.code = code;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }
}
