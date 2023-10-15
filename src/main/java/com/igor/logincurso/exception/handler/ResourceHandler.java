package com.igor.logincurso.exception.handler;

import com.igor.logincurso.exception.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> notFoundException(NotFoundException n){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(n.getMessage());
    }
}
