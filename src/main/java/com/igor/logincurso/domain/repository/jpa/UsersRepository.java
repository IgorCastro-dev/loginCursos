package com.igor.logincurso.domain.repository.jpa;

import com.igor.logincurso.domain.model.jpa.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
}
