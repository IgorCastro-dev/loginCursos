package com.igor.logincurso.infrastruture.payment;


import com.igor.logincurso.dto.payment.CustomerDto;
import com.igor.logincurso.dto.payment.OrderDto;
import com.igor.logincurso.dto.payment.PaymentDto;

public interface PaymentIntegration {

    CustomerDto createCustomer(CustomerDto customerDto);
    OrderDto createOrder(OrderDto orderDto);
    boolean processPayment(PaymentDto paymentDto);
}
