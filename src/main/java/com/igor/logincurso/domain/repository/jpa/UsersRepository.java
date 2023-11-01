package com.igor.logincurso.domain.repository.jpa;

import com.igor.logincurso.domain.model.jpa.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users,Long> {
    Optional<Users> findByEmail(String email);
}
