package com.igor.logincurso.domain.service;

import com.igor.logincurso.domain.model.redis.UserRecoveryCode;
import com.igor.logincurso.dto.EmailDto;

public interface UserRecoveryService {
    UserRecoveryCode sendRecoveryCode(EmailDto email);

    Boolean isValidCode(String recoveryCode, String email);
}
