package com.igor.logincurso.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String id;
    private String cpf;
    private String email;
    private String firstName;
    private String lastName;

}
