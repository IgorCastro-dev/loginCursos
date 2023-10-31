package com.igor.logincurso.domain.repository.redis;

import com.igor.logincurso.domain.model.redis.UserRecoveryCode;
import com.igor.logincurso.dto.EmailDto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRecoveryCodeRepository extends CrudRepository<UserRecoveryCode,String> {

    Optional<UserRecoveryCode> findByEmail(String email);
}
