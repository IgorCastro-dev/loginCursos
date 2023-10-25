package com.igor.logincurso.core;

import com.igor.logincurso.domain.model.Users;
import com.igor.logincurso.dto.payment.CustomerDto;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        var userToModelMapper = modelMapper.createTypeMap(Users.class, CustomerDto.class);
                userToModelMapper.addMapping(
                        user -> getFirstName(user.getName()),
                        (customerDto, value) -> customerDto.setFirstName((String) value));
                userToModelMapper.addMapping(
                        user -> getLastName(user.getName()),
                        (customerDto, value) -> customerDto.setLastName((String) value));
        return modelMapper;
    }

    private String getFirstName(String name) {
        String[] fullName = name.split(" ");
        return fullName[0];
    }

    private String getLastName(String name) {
        String[] fullName = name.split(" ");
        return fullName.length>1?fullName[fullName.length-1]:"";
    }
}
