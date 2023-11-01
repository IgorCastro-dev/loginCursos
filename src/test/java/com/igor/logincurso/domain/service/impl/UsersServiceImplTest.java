package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.model.jpa.UserType;
import com.igor.logincurso.domain.model.jpa.Users;
import com.igor.logincurso.domain.repository.jpa.UsersRepository;
import com.igor.logincurso.domain.service.UserTypeService;
import com.igor.logincurso.dto.UsersDto;
import com.igor.logincurso.exception.NotFoundException;
import com.igor.logincurso.modelmapper.UsersAssembler;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class UsersServiceImplTest {
    @Mock
    UserTypeService userTypeService;
    @Mock
    UsersAssembler usersAssembler;
    @Mock
    UsersRepository usersRepository;
    @InjectMocks
    UsersServiceImpl usersService;

    @Test
    void give_save_when_UserDtoIsOk_then_ReturnUsers(){
        UsersDto usersDto = new UsersDto();
        usersDto.setUserType_id(1L);
        Users users = new Users();
        Mockito.when(usersAssembler.dtoToEntity(usersDto)).thenReturn(users);
        UserType userType = new UserType();
        Mockito.when(userTypeService.findById(1L)).thenReturn(userType);
        Mockito.when(usersRepository.save(users)).thenReturn(users);
        Assertions.assertEquals(users,usersService.save(usersDto));
        Mockito.verify(userTypeService,Mockito.times(1)).findById(1L);
        Mockito.verify(usersRepository,Mockito.times(1)).save(users);
        Mockito.verify(usersAssembler,Mockito.times(1)).dtoToEntity(usersDto);
    }

    @Test
    void give_findByEmail_when_emailIsPresent_then_ReturnUsers(){
        Users users = new Users();
        Mockito.when(usersRepository.findByEmail("t@teste.com")).thenReturn(Optional.of(users));
        Assertions.assertEquals(users,usersService.findByEmail("t@teste.com"));
        Mockito.verify(usersRepository,Mockito.times(1)).findByEmail("t@teste.com");
    }

    @Test
    void give_findByEmail_when_emailIsNotPresent_then_NotFoundException(){
        Assertions.assertThrows(NotFoundException.class,()->usersService.findByEmail(null));
    }
}