package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.model.jpa.UserCredentials;
import com.igor.logincurso.domain.model.redis.UserRecoveryCode;
import com.igor.logincurso.domain.repository.redis.UserRecoveryCodeRepository;
import com.igor.logincurso.domain.service.UserRecoveryService;
import com.igor.logincurso.dto.EmailDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UserRecoveryServiceImpl implements UserRecoveryService {
    @Autowired
    private UserRecoveryCodeRepository userRecoveryCodeRepository;

    @Autowired
    private UserDetailsService userDetailsService;
    @Override
    public UserRecoveryCode sendRecoveryCode(EmailDto email) {
        String code = String.format("%04d",new Random().nextInt(10000));
        UserRecoveryCode userRecoveryCode = new UserRecoveryCode();
        Optional<UserRecoveryCode> userRecoveryCodeOptional = userRecoveryCodeRepository.findByEmail(email.getEmail());
        if (userRecoveryCodeOptional.isEmpty()){
            userDetailsService.loadUserByUsername(email.getEmail());
            userRecoveryCode.setEmail(email.getEmail());
        }else {
            userRecoveryCode = userRecoveryCodeOptional.get();
        }
        userRecoveryCode.setCode(code);
        userRecoveryCode.setDateTime(LocalDateTime.now());
        return userRecoveryCodeRepository.save(userRecoveryCode);
    }
}
