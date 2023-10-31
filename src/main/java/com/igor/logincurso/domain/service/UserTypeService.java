package com.igor.logincurso.domain.service;

import com.igor.logincurso.domain.model.jpa.UserType;

public interface UserTypeService {
    UserType findById(Long id);
}
