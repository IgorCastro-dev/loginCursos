package com.igor.logincurso.modelmapper;

import com.igor.logincurso.domain.model.jpa.UserPaymentInfo;
import com.igor.logincurso.dto.UserPaymentDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserPaymentAssembler {

    @Autowired
    private ModelMapper modelMapper;

    public UserPaymentInfo dtoToEntity(UserPaymentDto dto){
        return modelMapper.map(dto, UserPaymentInfo.class);
    }
}
