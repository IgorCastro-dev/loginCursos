package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.model.jpa.UserType;
import com.igor.logincurso.domain.model.jpa.Users;
import com.igor.logincurso.domain.repository.jpa.UsersRepository;
import com.igor.logincurso.domain.repository.redis.UserRecoveryCodeRepository;
import com.igor.logincurso.domain.service.UserTypeService;
import com.igor.logincurso.domain.service.UsersService;
import com.igor.logincurso.dto.UsersDto;
import com.igor.logincurso.exception.NotFoundException;
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

    @Autowired
    private UserRecoveryCodeRepository userRecoveryCodeRepository;

    @Override
    public Users save(UsersDto usersDto) {
        Users users = usersAssembler.dtoToEntity(usersDto);
        UserType userType = userTypeService.findById(usersDto.getUserType_id());
        users.setUserType(userType);
        return usersRepository.save(users);
    }

    @Override
    public Users findByEmail(String email) {
        return usersRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Usuário não encontrado")
        );
    }


}
