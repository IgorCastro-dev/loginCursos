package com.igor.logincurso.modelmapper.payment;

import com.igor.logincurso.dto.UserPaymentDto;
import com.igor.logincurso.dto.payment.CustomerDto;
import com.igor.logincurso.dto.payment.OrderDto;
import com.igor.logincurso.dto.payment.PaymentDto;
import com.igor.logincurso.dto.payment.PaymentDto.CreditCardDto;
public class PaymentMapper {
    public static PaymentDto build(CreditCardDto creditCardDto, CustomerDto customerDto, OrderDto orderDto){
        return PaymentDto.builder()
                .creditCard(creditCardDto)
                .orderId(orderDto.getId())
                .customerId(customerDto.getId())
                .build();

    }
}
