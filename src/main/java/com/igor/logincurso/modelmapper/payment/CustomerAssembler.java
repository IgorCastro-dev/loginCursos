package com.igor.logincurso.modelmapper.payment;

import com.igor.logincurso.domain.model.jpa.Users;
import com.igor.logincurso.dto.payment.CustomerDto;

public class CustomerAssembler {

    public static CustomerDto build(Users user){
        String[] fullName = user.getName().split(" ");
        String firstName = fullName[0];
        String lastName = fullName.length > 1 ? fullName[fullName.length - 1] : ".";
        return CustomerDto.builder()
                .firstName(firstName)
                .lastName(lastName)
                .cpf(user.getCpf())
                .email(user.getEmail())
                .build();
    }
}
