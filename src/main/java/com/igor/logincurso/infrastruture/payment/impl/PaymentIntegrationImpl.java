package com.igor.logincurso.infrastruture.payment.impl;

import com.igor.logincurso.dto.payment.CustomerDto;
import com.igor.logincurso.dto.payment.OrderDto;
import com.igor.logincurso.dto.payment.PaymentDto;
import com.igor.logincurso.infrastruture.payment.PaymentIntegration;

public class PaymentIntegrationImpl implements PaymentIntegration {
    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        return null;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public boolean processPayment(PaymentDto paymentDto) {
        return false;
    }
}
