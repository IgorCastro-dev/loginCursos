package com.igor.logincurso.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDto {
    @Email
    private String email;
}
