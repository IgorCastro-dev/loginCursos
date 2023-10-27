package com.igor.logincurso.domain.repository;

import com.igor.logincurso.domain.model.UserCredentials;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserDetailsRepository extends JpaRepository<UserCredentials,Long> {

    Optional<UserCredentials> findByUsername(String username);
}
