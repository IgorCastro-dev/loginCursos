package com.igor.logincurso.integration;

import com.igor.logincurso.infrastruture.email.EnvioEmailService;
import com.igor.logincurso.infrastruture.email.EnvioEmailService.Menssagem;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;

@SpringBootTest
public class MailIntegrationTest {

    @Autowired
    EnvioEmailService envioEmailService;
    @Test
    void enviarMenssagemWhenMenssagemOk() throws MessagingException {
        Menssagem menssagem = Menssagem.builder()
                                        .corpo("mensagem de teste")
                                        .assunto("Sucesso")
                                        .destinatario("manolonit3@gmail.com")
                                        .build();
        envioEmailService.enviar(menssagem);
    }
}
