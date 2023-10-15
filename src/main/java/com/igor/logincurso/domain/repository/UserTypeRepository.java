package com.igor.logincurso.domain.repository;

import com.igor.logincurso.domain.model.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType,Long> {
}
