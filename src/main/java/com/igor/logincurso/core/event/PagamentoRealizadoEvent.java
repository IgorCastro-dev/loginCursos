package com.igor.logincurso.core.event;

import com.igor.logincurso.domain.model.UserPaymentInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PagamentoRealizadoEvent {
    private UserPaymentInfo userPaymentInfo;
    private String nomeCurso;
}
