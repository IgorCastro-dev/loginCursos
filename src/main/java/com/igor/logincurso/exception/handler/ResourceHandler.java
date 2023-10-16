package com.igor.logincurso.exception.handler;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.igor.logincurso.exception.BadRequestException;
import com.igor.logincurso.exception.NotFoundException;
import com.igor.logincurso.exception.erro.ErroResponseDto;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResourceHandler {

    @Autowired
    private MessageSource messageSource;

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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ErroResponseDto> httpMessageNotReadableException(HttpMessageNotReadableException h){
        Throwable throwable = h.getCause();
        if (throwable instanceof InvalidFormatException){
            return criaInvalidFormatException((InvalidFormatException) throwable);
        }else if (throwable instanceof PropertyBindingException) {
            return criaPropertyBindingException((PropertyBindingException) throwable);
        }
        ErroResponseDto erro = ErroResponseDto.builder()
                .message("Ocorpo da requisição está inválido")
                .httpStatus(HttpStatus.BAD_REQUEST)
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<ErroResponseDto> methodArgumentNotValidException(MethodArgumentNotValidException ex, BindingResult bindingResult){
        String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto";
        List<ErroResponseDto.Field> erroField = bindingResult.getFieldErrors().stream()
                .map(fieldError ->{
                    String message = messageSource.getMessage(fieldError, LocaleContextHolder.getLocale());
                    return ErroResponseDto.Field.builder()
                            .name(fieldError.getField())
                            .userMessage(message)
                            .build();
                })
                .collect(Collectors.toList());
        ErroResponseDto erro = ErroResponseDto.builder().httpStatus(HttpStatus.BAD_REQUEST)
                .message(detail)
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .fields(erroField)
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(ConstraintViolationException ex) {
        ErroResponseDto erro = ErroResponseDto.builder().httpStatus(HttpStatus.BAD_REQUEST)
                .message(ex.getCause().getMessage())
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return new ResponseEntity<>(erro, HttpStatus.BAD_REQUEST);
    }

    private ResponseEntity<ErroResponseDto> criaPropertyBindingException(PropertyBindingException ex) {
        String path = ex.getPath().stream()
                .map((ref)->ref.getFieldName())
                .collect(Collectors.joining("."));

        String detail = String.format("A propriedade %s não pertence ao corpo da requisição",path);

        ErroResponseDto erro = ErroResponseDto.builder()
                .message(detail)
                .httpStatus(HttpStatus.BAD_REQUEST)
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }

    private ResponseEntity<ErroResponseDto> criaInvalidFormatException(InvalidFormatException ex) {
        String path = ex.getPath().stream()
                .map((ref)->ref.getFieldName())
                .collect(Collectors.joining("."));
        ErroResponseDto erro = ErroResponseDto.builder()
                .message(String.format("A propriedade %s que recebeu o valor %s está inválido, insira o tipo %s",path,ex.getValue(),ex.getTargetType().getSimpleName()))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .httpStatusCode(HttpStatus.BAD_REQUEST.value())
                .build();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);
    }
}
