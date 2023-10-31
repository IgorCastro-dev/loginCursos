package com.igor.logincurso.domain.repository.jpa;

import com.igor.logincurso.domain.model.jpa.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserTypeRepository extends JpaRepository<UserType,Long> {
}
