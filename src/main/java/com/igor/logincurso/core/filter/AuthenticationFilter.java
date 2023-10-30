package com.igor.logincurso.core.filter;

import com.igor.logincurso.domain.model.UserCredentials;
import com.igor.logincurso.domain.repository.UserDetailsRepository;
import com.igor.logincurso.domain.service.TokenService;
import com.igor.logincurso.exception.NotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

public class AuthenticationFilter extends OncePerRequestFilter {
    private TokenService tokenService;

    private UserDetailsRepository userDetailsRepository;

    public AuthenticationFilter(TokenService tokenService,UserDetailsRepository userDetailsRepository){
        this.tokenService = tokenService;
        this.userDetailsRepository = userDetailsRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = getBearerToken(request);
        if (Objects.nonNull(token)){
            authByToken(token);
        }
        filterChain.doFilter(request,response);
    }

    private void authByToken(String token) {
        String login = tokenService.validateToken(token);
        UserCredentials userCredentials = userDetailsRepository.findByUsername(login).orElseThrow(
                () -> new NotFoundException("Usuário não encontrado")
        );
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userCredentials,null,userCredentials.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

    }

    protected String getBearerToken(HttpServletRequest request) {
        String token = request.getHeader("Authorization");

        if (Objects.isNull(token) || !token.startsWith("Bearer")){
            return null;
        }
        return token.substring(7);
    }
}
