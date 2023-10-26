package com.igor.logincurso.integration;

import com.igor.logincurso.domain.model.UserPaymentInfo;
import com.igor.logincurso.domain.repository.UserPaymentInfoRepository;
import com.igor.logincurso.infrastruture.email.EnvioEmailService;
import com.igor.logincurso.infrastruture.email.EnvioEmailService.Menssagem;
import freemarker.template.TemplateException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.mail.MessagingException;
import javax.transaction.Transactional;
import java.io.IOException;
import java.util.HashMap;

@SpringBootTest
public class MailIntegrationTest {

    @Autowired
    EnvioEmailService envioEmailService;

    @Autowired
    UserPaymentInfoRepository userPaymentInfoRepository;

    @Transactional
    @Test
    void enviarMenssagemWhenMenssagemOk() throws MessagingException, TemplateException, IOException {

        UserPaymentInfo userPaymentInfo = userPaymentInfoRepository.findById(1L).get();

        var map = new HashMap<String,Object>();
        map.put("userPaymentInfo", userPaymentInfo);
        map.put("curso", "Curso do Igor");
        Menssagem menssagem = Menssagem.builder()
                                        .corpo("pagamento-confirmado.html")
                                        .assunto("Sucesso")
                                        .destinatario("manolonit3@gmail.com")
                                        .variaveis(map)
                                        .build();
        envioEmailService.enviar(menssagem);
    }
}
