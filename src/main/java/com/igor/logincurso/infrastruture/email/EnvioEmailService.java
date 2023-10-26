package com.igor.logincurso.infrastruture.email;

import freemarker.template.TemplateException;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import lombok.Singular;
import org.springframework.validation.annotation.Validated;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

public interface EnvioEmailService {

    void enviar(Menssagem menssagem) throws MessagingException, IOException, TemplateException;

    @Validated
    @Builder
    @Data
    class Menssagem{

        @Singular
        private Set<String> destinatarios;

        @NonNull
        private String assunto;

        @NonNull
        private String corpo;

        @Singular("variavel")
        private Map<String,Object> variaveis;
    }
}
