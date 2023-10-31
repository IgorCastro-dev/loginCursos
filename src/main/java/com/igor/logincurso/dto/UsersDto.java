package com.igor.logincurso.dto;

import lombok.Data;
import org.hibernate.validator.constraints.br.CPF;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class UsersDto {
    @NotBlank
    private String name;
    @Email
    private String email;
    @Min(value = 11)
    private String phone;

    @CPF
    private String cpf;

    private LocalDate dtSubscription=LocalDate.now();

    private LocalDate dtExpiration=LocalDate.now();

    @NotNull
    private Long userType_id;

}
