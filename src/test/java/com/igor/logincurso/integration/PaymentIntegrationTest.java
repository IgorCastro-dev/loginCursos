package com.igor.logincurso.integration;

import com.igor.logincurso.dto.payment.CustomerDto;
import com.igor.logincurso.infrastruture.payment.PaymentIntegration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PaymentIntegrationTest {

    @Autowired
    PaymentIntegration paymentIntegration;
    @Test
    void payment() {
        CustomerDto dto = new CustomerDto(null,"351.909.210-78","teste1@teste2","Igor","Castro");
        paymentIntegration.createCustomer(dto);
    }
}
