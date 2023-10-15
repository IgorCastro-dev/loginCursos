package com.igor.logincurso.domain.repository;

import com.igor.logincurso.domain.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
