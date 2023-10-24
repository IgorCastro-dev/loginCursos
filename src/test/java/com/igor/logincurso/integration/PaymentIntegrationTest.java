package com.igor.logincurso.integration;

import com.igor.logincurso.dto.payment.CustomerDto;
import com.igor.logincurso.dto.payment.OrderDto;
import com.igor.logincurso.dto.payment.PaymentDto;
import com.igor.logincurso.dto.payment.PaymentDto.CreditCardDto;
import com.igor.logincurso.infrastruture.payment.PaymentIntegration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class PaymentIntegrationTest {

    @Autowired
    PaymentIntegration paymentIntegration;
    @Test
    void createCustomerWhenDtoOk() {
        CustomerDto dto = new CustomerDto(null,"191.408.950-25","teste1@teste2","Igor","Castro");
        paymentIntegration.createCustomer(dto);
    }

    @Test
    void createOrderWhenDtoOk() {
        OrderDto dto = new OrderDto(null,"6530a413e54d4937ec8827a8", BigDecimal.ZERO,"MONTH22");
        paymentIntegration.createOrder(dto);
    }
    @Test
    void createPaymentWhenDtoOk() {
        CreditCardDto creditCardDto = new CreditCardDto(123L,"351.909.210-78",0L,06L,"123123123123",2024L);
        PaymentDto paymentDto = new PaymentDto(creditCardDto,"6530a413e54d4937ec8827a8","6530ac68e54d4937ec8827ad");
        paymentIntegration.processPayment(paymentDto);
    }
}
