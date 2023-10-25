package com.igor.logincurso.modelmapper.payment;

import com.igor.logincurso.dto.PaymentProcessDto;
import com.igor.logincurso.dto.UserPaymentDto;
import com.igor.logincurso.dto.payment.CustomerDto;
import com.igor.logincurso.dto.payment.PaymentDto.CreditCardDto;

public class CreditCardAssembler {

    public static CreditCardDto build(UserPaymentDto userPaymentDto, CustomerDto customerDto){
        return CreditCardDto.builder()
                .documentNumber(customerDto.getCpf())
                .number(userPaymentDto.getCardNumber())
                .cvv(Long.parseLong(userPaymentDto.getCardSecurityCode()))
                .month(userPaymentDto.getCardExpirationMonth())
                .year(userPaymentDto.getCardExpirationYear())
                .installments(userPaymentDto.getInstallments())
                .build();
    }
}
