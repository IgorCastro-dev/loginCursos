package com.igor.logincurso.domain.controller;

import antlr.Token;
import com.igor.logincurso.domain.service.AuthenticationService;
import com.igor.logincurso.domain.service.TokenService;
import com.igor.logincurso.dto.LoginDto;
import com.igor.logincurso.dto.TokenDto;
import com.igor.logincurso.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticar(
            @RequestBody @Valid LoginDto loginDto){
        return ResponseEntity.ok(authenticationService.auth(loginDto));
    }
}
