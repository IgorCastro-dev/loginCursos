package com.igor.logincurso.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class UserDetailsDto {
    @Email
    private String email;
    private String recoveryCode;
    @NotBlank
    private String password;
}
