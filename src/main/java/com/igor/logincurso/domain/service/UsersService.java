package com.igor.logincurso.domain.service;

import com.igor.logincurso.domain.model.Users;
import com.igor.logincurso.dto.UsersDto;

public interface UsersService{
    Users save(UsersDto usersDto);
}
