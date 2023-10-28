package com.igor.logincurso.domain.service;

import com.igor.logincurso.dto.LoginDto;
import com.igor.logincurso.dto.TokenDto;

public interface AuthenticationService {
    TokenDto auth(LoginDto loginDto);
}
