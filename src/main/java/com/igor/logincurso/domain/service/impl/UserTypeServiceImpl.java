package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.model.jpa.UserType;
import com.igor.logincurso.domain.repository.jpa.UserTypeRepository;
import com.igor.logincurso.domain.service.UserTypeService;
import com.igor.logincurso.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTypeServiceImpl implements UserTypeService {

    @Autowired
    UserTypeRepository userTypeRepository;
    @Override
    public UserType findById(Long id) {
        UserType userType = userTypeRepository.findById(id)
                .orElseThrow(()->new NotFoundException("UserType não encontrado"));
        return userType;
    }
}
