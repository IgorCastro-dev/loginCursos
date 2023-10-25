package com.igor.logincurso.modelmapper.payment;

import com.igor.logincurso.domain.model.Users;
import com.igor.logincurso.dto.payment.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CustomerAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public CustomerDto userToCustomer(Users user){
        return modelMapper.map(user, CustomerDto.class);
    }
}
