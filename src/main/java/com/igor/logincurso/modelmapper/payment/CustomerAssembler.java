package com.igor.logincurso.modelmapper.payment;

import com.igor.logincurso.domain.model.Users;
import com.igor.logincurso.dto.payment.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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
