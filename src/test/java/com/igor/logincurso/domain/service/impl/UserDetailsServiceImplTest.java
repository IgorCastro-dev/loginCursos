package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.model.jpa.UserCredentials;
import com.igor.logincurso.domain.repository.jpa.UserDetailsRepository;
import com.igor.logincurso.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserDetailsServiceImplTest {
    @Mock
    private UserDetailsRepository userDetailsRepository;
    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;
    @Test
    void give_loadUserByUsername_when_usernameIsPresent_then_ReturnUserDetails() {
        UserCredentials userCredentials = new UserCredentials();
        Mockito.when(userDetailsRepository.findByUsername("t@teste.com")).thenReturn(Optional.of(userCredentials));
        Assertions.assertEquals(userCredentials,userDetailsService.loadUserByUsername("t@teste.com"));
        Mockito.verify(userDetailsRepository,Mockito.times(1)).findByUsername("t@teste.com");
    }

    @Test
    void give_loadUserByUsername_when_usernameIsNotPresent_then_ReturnNotFoundException() {
        Assertions.assertThrows(NotFoundException.class,()->userDetailsService.loadUserByUsername(null));
    }
}