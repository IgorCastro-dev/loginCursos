package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.service.UserRecoveryService;
import com.igor.logincurso.domain.service.UsersService;
import com.igor.logincurso.dto.EmailDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UsersServiceImplTest {

    @Autowired
    UserRecoveryService userRecoveryService;

    @Test
    void sendRecoveryCode() {
        userRecoveryService.sendRecoveryCode(new EmailDto("test@gmail.com"));
    }
}