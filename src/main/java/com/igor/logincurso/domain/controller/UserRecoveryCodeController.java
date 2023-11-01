package com.igor.logincurso.domain.controller;

import com.igor.logincurso.domain.service.UserRecoveryService;
import com.igor.logincurso.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/users")
public class UserRecoveryCodeController {

    @Autowired
    private UserRecoveryService userRecoveryService;

    @PostMapping("recovery-code/send")
    public ResponseEntity<Void> sendRecoveryCode(@RequestBody @Valid EmailDto email){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(userRecoveryService.sendRecoveryCode(email));
    }

    @GetMapping("recovery-code/valid")
    public ResponseEntity<Boolean> validRecoveryCode(
            @PathParam("recoveryCode") String recoveryCode,
            @PathParam("email") String email){
        return ResponseEntity.ok(userRecoveryService.isValidCode(recoveryCode,email));
    }
}
