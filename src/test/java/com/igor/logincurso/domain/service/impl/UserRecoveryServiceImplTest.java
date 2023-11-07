package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.core.event.EnvioRecoveryCodeEvent;
import com.igor.logincurso.domain.model.jpa.UserCredentials;
import com.igor.logincurso.domain.model.redis.UserRecoveryCode;
import com.igor.logincurso.domain.repository.redis.UserRecoveryCodeRepository;
import com.igor.logincurso.dto.EmailDto;
import com.igor.logincurso.dto.UserDetailsDto;
import com.igor.logincurso.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.util.ReflectionTestUtils;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class UserRecoveryServiceImplTest {
    @InjectMocks
    private UserRecoveryServiceImpl userRecoveryService;
    @Mock
    private UserRecoveryCodeRepository userRecoveryCodeRepository;
    @Mock
    private UserDetailsService userDetailsService;
    @Mock
    private ApplicationEventPublisher publisher;

    private final String EMAIL = "t@teste.com";
    private final String CODE = "1212";
    private final String TIME = "5";
    private UserRecoveryCode userRecoveryCode;
    private UserDetailsDto userDetailsDto;
    private EmailDto emailDto;
    @BeforeEach
    private void setup(){
        UserRecoveryCode userRecoveryCode = new UserRecoveryCode();
        userRecoveryCode.setCode(CODE);
        userRecoveryCode.setEmail(EMAIL);
        this.userRecoveryCode = userRecoveryCode;
        UserDetailsDto userDetailsDto = new UserDetailsDto();
        userDetailsDto.setEmail(EMAIL);
        userDetailsDto.setPassword("1234");
        userDetailsDto.setRecoveryCode(CODE);
        this.userDetailsDto = userDetailsDto;
        EmailDto emailDto = new EmailDto();
        emailDto.setEmail(EMAIL);
        this.emailDto = emailDto;
    }

    @Test
    void give_sendRecoveryCode_when_emailIsPresent_then_sendRecoveryCode() {
        UserRecoveryCode userRecoveryCode = new UserRecoveryCode();
        Mockito.when(userRecoveryCodeRepository.findByEmail(emailDto.getEmail())).thenReturn(Optional.of(userRecoveryCode));
        assertNull(userRecoveryService.sendRecoveryCode(emailDto));
        Mockito.verify(userRecoveryCodeRepository,Mockito.times(1)).findByEmail(emailDto.getEmail());
        Mockito.verify(publisher,Mockito.times(1)).publishEvent(Mockito.any(EnvioRecoveryCodeEvent.class));
    }

    @Test
    void give_sendRecoveryCode_when_emailIsNotPresent_then_sendRecoveryCode() {
        UserRecoveryCode userRecoveryCode = new UserRecoveryCode();
        Mockito.when(userRecoveryCodeRepository.findByEmail(emailDto.getEmail())).thenReturn(Optional.empty());
        assertNull(userRecoveryService.sendRecoveryCode(emailDto));
        Mockito.verify(userDetailsService,Mockito.times(1)).loadUserByUsername(emailDto.getEmail());
        Mockito.verify(userRecoveryCodeRepository,Mockito.times(1)).findByEmail(emailDto.getEmail());
        Mockito.verify(publisher,Mockito.times(1)).publishEvent(Mockito.any(EnvioRecoveryCodeEvent.class));
    }

    @Test
    void give_isValidCode_when_emailAndRecoveryCodeIsOk_then_returnTrue() {
        ReflectionTestUtils.setField(userRecoveryService,"time",TIME);
        Mockito.when(userRecoveryCodeRepository.findByEmail(userRecoveryCode.getEmail())).thenReturn(Optional.of(userRecoveryCode));
        Assertions.assertTrue(userRecoveryService.isValidCode("1212","t@teste.com"));
        Mockito.verify(userRecoveryCodeRepository,Mockito.times(1)).findByEmail("t@teste.com");
    }

    @Test
    void give_isValidCode_when_userRecoveryIsNotPresent_then_returnNotFoundException() {
        Assertions.assertEquals("Usuário não encontrado",
                Assertions.assertThrows(NotFoundException.class,
                        ()->userRecoveryService.isValidCode(CODE,null))
                        .getMessage());

    }

    @Test
    void give_isValidCode_when_recoveryIsNotCorrect_then_returnFalse() {
        ReflectionTestUtils.setField(userRecoveryService,"time",TIME);
        Mockito.when(userRecoveryCodeRepository.findByEmail(userRecoveryCode.getEmail())).thenReturn(Optional.of(userRecoveryCode));
        Assertions.assertFalse(userRecoveryService.isValidCode("1313","t@teste.com"));
        Mockito.verify(userRecoveryCodeRepository,Mockito.times(1)).findByEmail("t@teste.com");
    }

    @Test
    void give_isValidCode_when_nowIsMoreThanTimeout_then_returnFalse() {
        userRecoveryCode.setDateTime(LocalDateTime.now().minusMinutes(6));
        ReflectionTestUtils.setField(userRecoveryService,"time",TIME);
        Mockito.when(userRecoveryCodeRepository.findByEmail(userRecoveryCode.getEmail())).thenReturn(Optional.of(userRecoveryCode));
        Assertions.assertFalse(userRecoveryService.isValidCode("1212","t@teste.com"));
        Mockito.verify(userRecoveryCodeRepository,Mockito.times(1)).findByEmail("t@teste.com");
    }

    @Test
    void give_updatePassword_when_userDetailsDtoIsValid_then_saveNewPassword() {
        ReflectionTestUtils.setField(userRecoveryService,"time",TIME);
        Mockito.when(userRecoveryCodeRepository.findByEmail(userRecoveryCode.getEmail())).thenReturn(Optional.of(userRecoveryCode));
        UserCredentials userCredentials = new UserCredentials();
        Mockito.when(userDetailsService.loadUserByUsername(userDetailsDto.getEmail())).thenReturn(userCredentials);
        assertNull(userRecoveryService.updatePassword(userDetailsDto));
        Mockito.verify(userRecoveryCodeRepository,Mockito.times(1)).findByEmail(userRecoveryCode.getEmail());
        Mockito.verify(userDetailsService,Mockito.times(1)).loadUserByUsername(userDetailsDto.getEmail());
    }

}