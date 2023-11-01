package com.igor.logincurso.core.listener;

import com.igor.logincurso.core.event.EnvioRecoveryCodeEvent;
import com.igor.logincurso.domain.model.redis.UserRecoveryCode;
import com.igor.logincurso.exception.EmailException;
import com.igor.logincurso.infrastruture.email.EnvioEmailService;
import com.igor.logincurso.infrastruture.email.EnvioEmailService.Menssagem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class NotificacaoEnvioRecoveryCode {

    @Autowired
    private EnvioEmailService envioEmailService;

    @EventListener
    private void aoEnviarCodigo(EnvioRecoveryCodeEvent event){
        try {
            UserRecoveryCode userRecoveryCode = event.getUserRecoveryCode();
            Menssagem menssagem = Menssagem.builder()
                    .assunto("Código de verificação")
                    .destinatario(userRecoveryCode.getEmail())
                    .corpo("envio-user-recovery-code.html")
                    .variavel("userRecoveryCode",userRecoveryCode)
                    .build();
            envioEmailService.enviar(menssagem);
        }catch (Exception e){
            throw new EmailException(e.getMessage());
        }
    }
}
