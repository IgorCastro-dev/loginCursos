package com.igor.logincurso.domain.service;

import antlr.Token;
import org.springframework.security.core.Authentication;

public interface TokenService {

    String getToken(Authentication auth);
}
