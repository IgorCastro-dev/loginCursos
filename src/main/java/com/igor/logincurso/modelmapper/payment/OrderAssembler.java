package com.igor.logincurso.modelmapper.payment;

import com.igor.logincurso.dto.PaymentProcessDto;
import com.igor.logincurso.dto.payment.CustomerDto;
import com.igor.logincurso.dto.payment.OrderDto;
public class OrderAssembler {

    public static OrderDto build(CustomerDto customerDto, PaymentProcessDto paymentProcessDto){
        return OrderDto.builder()
                .customerId(customerDto.getId())
                .discount(paymentProcessDto.getDiscount())
                .productAcronym(paymentProcessDto.getProductKey())
                .build();
    }
}
