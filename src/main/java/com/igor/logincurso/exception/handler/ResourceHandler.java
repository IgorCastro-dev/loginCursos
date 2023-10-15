package com.igor.logincurso.exception.handler;

import com.igor.logincurso.exception.BadRequestException;
import com.igor.logincurso.exception.NotFoundException;
import com.igor.logincurso.exception.erro.ErroResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErroResponseDto> notFoundException(NotFoundException n){
        ErroResponseDto erro = ErroResponseDto.builder()
                .message(n.getMessage())
                .httpStatus(HttpStatus.NOT_FOUND)
                .httpStatusCode(HttpStatus.NOT_FOUND.value())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);
    }
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErroResponseDto> badRequestException(BadRequestException b){
        ErroResponseDto erro = ErroResponseDto.builder()
                .message(b.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
