package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.service.AuthenticationService;
import com.igor.logincurso.domain.service.TokenService;
import com.igor.logincurso.dto.LoginDto;
import com.igor.logincurso.dto.TokenDto;
import com.igor.logincurso.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @Override
    public TokenDto auth(LoginDto loginDto) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(),loginDto.getPassword());
            Authentication auth = authenticationManager.authenticate(authenticationToken);
            String token = tokenService.getToken(auth);
            return TokenDto.builder().token(token).type("Bearer").build();
        }catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }
}
