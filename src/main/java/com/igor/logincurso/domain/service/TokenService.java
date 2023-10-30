package com.igor.logincurso.domain.service;
import org.springframework.security.core.Authentication;

public interface TokenService{

    String getToken(Authentication auth);

    String validateToken(String token);
}
