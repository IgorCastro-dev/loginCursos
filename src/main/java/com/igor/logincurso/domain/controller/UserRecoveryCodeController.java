package com.igor.logincurso.domain.controller;

import com.igor.logincurso.domain.model.redis.UserRecoveryCode;
import com.igor.logincurso.domain.service.UserRecoveryService;
import com.igor.logincurso.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserRecoveryCodeController {

    @Autowired
    private UserRecoveryService userRecoveryService;

    @PostMapping("send-recovery-code")
    public ResponseEntity<UserRecoveryCode> sendRecoveryCode(@RequestBody @Valid EmailDto email){
        return ResponseEntity.status(HttpStatus.CREATED).body(userRecoveryService.sendRecoveryCode(email));
    }
}
