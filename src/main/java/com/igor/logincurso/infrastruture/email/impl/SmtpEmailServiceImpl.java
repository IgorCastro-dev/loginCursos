package com.igor.logincurso.infrastruture.email.impl;

import com.igor.logincurso.infrastruture.email.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class SmtpEmailServiceImpl implements EnvioEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${login.email.remetente}")
    private String remetente;
    @Override
    public void enviar(Menssagem menssagem) throws MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        helper.setFrom(remetente);
        helper.setTo(menssagem.getDestinatarios().toArray(new String[0]));
        helper.setSubject(menssagem.getAssunto());
        helper.setText(menssagem.getCorpo());
        javaMailSender.send(mimeMessage);
    }
}
