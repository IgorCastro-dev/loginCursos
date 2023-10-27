package com.igor.logincurso.core.listener;

import com.igor.logincurso.domain.model.UserPaymentInfo;
import com.igor.logincurso.core.event.PagamentoRealizadoEvent;
import com.igor.logincurso.exception.EmailException;
import com.igor.logincurso.infrastruture.email.EnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import java.util.HashMap;

@Component
public class NotificacaoPagamentoConfirmado {

    @Autowired
    private EnvioEmailService envioEmailService;

    @EventListener
    public void aoRealizarPagamento(PagamentoRealizadoEvent event){
        try{
            UserPaymentInfo userPaymentInfo = event.getUserPaymentInfo();
            String nomeCurso = event.getNomeCurso();
            var map = new HashMap<String,Object>();
            map.put("userPaymentInfo", userPaymentInfo);
            map.put("curso", nomeCurso);
            EnvioEmailService.Menssagem menssagem = EnvioEmailService.Menssagem.builder()
                    .assunto("Pagamento confirmado")
                    .corpo("pagamento-confirmado.html")
                    .destinatario(userPaymentInfo.getUsers().getEmail())
                    .variaveis(map)
                    .build();
            envioEmailService.enviar(menssagem);
        }catch (Exception e){
            throw new EmailException(e.getMessage());
        }
    }
}
