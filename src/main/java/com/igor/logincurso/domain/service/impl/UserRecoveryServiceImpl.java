package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.core.event.EnvioRecoveryCodeEvent;
import com.igor.logincurso.domain.model.redis.UserRecoveryCode;
import com.igor.logincurso.domain.repository.redis.UserRecoveryCodeRepository;
import com.igor.logincurso.domain.service.UserRecoveryService;
import com.igor.logincurso.dto.EmailDto;
import com.igor.logincurso.exception.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class UserRecoveryServiceImpl implements UserRecoveryService {
    @Value("${webservice.igor.redis.recoverycode.timeout}")
    private String time;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private UserRecoveryCodeRepository userRecoveryCodeRepository;
    @Autowired
    private UserDetailsService userDetailsService;

    @Transactional
    @Override
    public Void sendRecoveryCode(EmailDto email) {
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
        publisher.publishEvent(new EnvioRecoveryCodeEvent(this,userRecoveryCode));
        userRecoveryCodeRepository.save(userRecoveryCode);
        return null;
    }

    @Override
    public Boolean isValidCode(String recoveryCode, String email) {
        UserRecoveryCode userRecoveryCode = userRecoveryCodeRepository.findByEmail(email).orElseThrow(
                () -> new NotFoundException("Usuário não encontrado")
        );
        LocalDateTime timeout = userRecoveryCode.getDateTime().plusMinutes(Long.parseLong(time));
        LocalDateTime now = LocalDateTime.now();

        if (recoveryCode.equals(userRecoveryCode.getCode()) && now.isBefore(timeout)){
            return true;
        }
        return false;
    }
}
