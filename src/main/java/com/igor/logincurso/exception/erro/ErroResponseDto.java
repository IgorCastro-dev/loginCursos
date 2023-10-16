package com.igor.logincurso.exception.erro;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErroResponseDto {
    private String message;
    private HttpStatus httpStatus;
    private Integer httpStatusCode;
    private List<Field> fields;

    @Getter
    @Builder
    public static class Field{
        private String name;
        private String userMessage;
    }
}
