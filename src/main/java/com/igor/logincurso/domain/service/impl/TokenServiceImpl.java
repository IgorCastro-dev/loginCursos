package com.igor.logincurso.domain.service.impl;


import com.igor.logincurso.domain.model.jpa.UserCredentials;
import com.igor.logincurso.domain.service.TokenService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${webservice.igor.jwt.expiration}")
    private String expiration;

    @Value("${webservice.igor.jwt.secret}")
    private String secret;
    public String getToken(Authentication auth) {
        UserCredentials user = (UserCredentials) auth.getPrincipal();
        Date today = new Date();
        Date expirationDate = new Date(today.getTime() + Long.parseLong(expiration));
        return Jwts.builder()
                .setIssuer("Igor")
                .setSubject(user.getUsername())
                .setIssuedAt(today)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    @Override
    public String validateToken(String token) {
        try{
            return Jwts.parser().setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        }catch (Exception e){
            return "";
        }
    }
}
