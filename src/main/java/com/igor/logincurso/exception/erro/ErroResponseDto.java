package com.igor.logincurso.exception.erro;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class ErroResponseDto {
    private String message;
    private HttpStatus httpStatus;
    private Integer httpStatusCode;
}
