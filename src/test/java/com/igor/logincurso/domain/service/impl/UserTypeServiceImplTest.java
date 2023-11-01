package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.model.jpa.UserType;
import com.igor.logincurso.domain.repository.jpa.UserTypeRepository;
import com.igor.logincurso.exception.NotFoundException;
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
class UserTypeServiceImplTest {

    @Mock
    UserTypeRepository userTypeRepository;

    @InjectMocks
    UserTypeServiceImpl userTypeService;

    @Test
    void give_findById_whenIdIsPresent_then_ReturnUserType(){
        UserType userType = new UserType();
        userType.setId(1L);
        Mockito.when(userTypeRepository.findById(1L)).thenReturn(Optional.of(userType));
        Assertions.assertEquals(userType,userTypeService.findById(1L));
        Mockito.verify(userTypeRepository,Mockito.times(1)).findById(1L);
    }
    @Test
    void give_findById_whenIdIsNotPresent_then_ReturnNotFoundException(){
        Assertions.assertThrows(NotFoundException.class,()->userTypeService.findById(null));
    }
}