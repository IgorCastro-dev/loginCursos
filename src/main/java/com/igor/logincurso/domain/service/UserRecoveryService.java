package com.igor.logincurso.domain.service;

import com.igor.logincurso.domain.model.redis.UserRecoveryCode;
import com.igor.logincurso.dto.EmailDto;
import com.igor.logincurso.dto.UserDetailsDto;

public interface UserRecoveryService {
    Void sendRecoveryCode(EmailDto email);

    Boolean isValidCode(String recoveryCode, String email);

    Void updatePassword(UserDetailsDto userDetailsDto);
}
