package com.example.demo.model.exception;

public class NoPersonFoundException extends RuntimeException{
    private String message;
    public NoPersonFoundException(String text) {
        this.message = text;
    }
    @Override
    public String getMessage() {
        return message;
    }
}
