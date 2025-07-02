package com.example.ApiRestFull.exception;

public class ExceptionCustomized extends RuntimeException {

    private String code;

    public ExceptionCustomized(String message, String code) {
        super(message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
