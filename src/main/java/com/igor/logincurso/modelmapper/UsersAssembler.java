package com.igor.logincurso.modelmapper;

import com.igor.logincurso.domain.model.jpa.Users;
import com.igor.logincurso.dto.UsersDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UsersAssembler {
    @Autowired
    private ModelMapper modelMapper;

    public UsersDto entityToDto(Users users){
        return modelMapper.map(users, UsersDto.class);
    }

    public Users dtoToEntity(UsersDto usersDto){
        return modelMapper.map(usersDto, Users.class);
    }
}
