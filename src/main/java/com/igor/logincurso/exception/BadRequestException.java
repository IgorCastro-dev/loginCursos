package com.igor.logincurso.exception;

public class BadRequestException extends RuntimeException{
    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException(String message,Throwable t) {
        super(message,t);
    }
}
