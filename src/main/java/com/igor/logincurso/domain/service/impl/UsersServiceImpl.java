package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.model.UserType;
import com.igor.logincurso.domain.model.Users;
import com.igor.logincurso.domain.repository.UsersRepository;
import com.igor.logincurso.domain.service.UserTypeService;
import com.igor.logincurso.domain.service.UsersService;
import com.igor.logincurso.dto.UsersDto;
import com.igor.logincurso.modelmapper.UsersAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsersServiceImpl implements UsersService {
    @Autowired
    private UserTypeService userTypeService;
    @Autowired
    private UsersAssembler usersAssembler;
    @Autowired
    private UsersRepository usersRepository;

    @Override
    public Users save(UsersDto usersDto) {
        Users users = usersAssembler.dtoToEntity(usersDto);
        UserType userType = userTypeService.findById(usersDto.getUserType_id());
        users.setUserType(userType);
        return usersRepository.save(users);
    }
}
