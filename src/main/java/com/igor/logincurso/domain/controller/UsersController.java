package com.igor.logincurso.domain.controller;

import com.igor.logincurso.domain.model.Users;
import com.igor.logincurso.domain.service.UsersService;
import com.igor.logincurso.dto.UsersDto;
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
public class UsersController {

    @Autowired
    private UsersService usersService;

    @PostMapping
    public ResponseEntity<Users> save(@RequestBody @Valid UsersDto usersDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(usersService.save(usersDto));
    }
}
