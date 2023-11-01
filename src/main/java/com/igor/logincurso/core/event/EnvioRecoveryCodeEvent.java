package com.igor.logincurso.core.event;

import com.igor.logincurso.domain.model.redis.UserRecoveryCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class EnvioRecoveryCodeEvent extends ApplicationEvent {
    private UserRecoveryCode userRecoveryCode;

    public EnvioRecoveryCodeEvent(Object source,UserRecoveryCode userRecoveryCode) {
        super(source);
        this.userRecoveryCode = userRecoveryCode;
    }
}
