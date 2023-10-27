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
        return new ModelMapper();
    }

}
