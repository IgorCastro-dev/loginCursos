package com.igor.logincurso.domain.service;

import com.igor.logincurso.domain.model.jpa.Users;
import com.igor.logincurso.dto.EmailDto;
import com.igor.logincurso.dto.UsersDto;

public interface UsersService{
    Users save(UsersDto usersDto);

    Users findByEmail(String email);
}
