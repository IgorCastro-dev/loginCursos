package com.igor.logincurso.core.event;

import com.igor.logincurso.domain.model.jpa.UserPaymentInfo;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

@Getter
@Setter
public class PagamentoRealizadoEvent extends ApplicationEvent {
    private UserPaymentInfo userPaymentInfo;
    private String nomeCurso;

    public PagamentoRealizadoEvent(Object source,String nomeCurso,UserPaymentInfo userPaymentInfo) {
        super(source);
        this.nomeCurso=nomeCurso;
        this.userPaymentInfo=userPaymentInfo;
    }
}
