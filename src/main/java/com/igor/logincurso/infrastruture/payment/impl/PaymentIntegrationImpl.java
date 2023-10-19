package com.igor.logincurso.infrastruture.payment.impl;

import com.igor.logincurso.dto.payment.CustomerDto;
import com.igor.logincurso.dto.payment.OrderDto;
import com.igor.logincurso.dto.payment.PaymentDto;
import com.igor.logincurso.infrastruture.payment.PaymentIntegration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;

@Component
public class PaymentIntegrationImpl implements PaymentIntegration {
    private RestTemplate restTemplate;
    public PaymentIntegrationImpl(){
        this.restTemplate = new RestTemplate();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        try {
            HttpHeaders httpHeaders = getHttpHeaders();
            HttpEntity<CustomerDto> request = new HttpEntity<>(customerDto,httpHeaders);
            ResponseEntity<CustomerDto> response = restTemplate.exchange(
                    "http://localhost:8081/v1/customer", HttpMethod.POST,
                    request,CustomerDto.class);
            return response.getBody();
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        return null;
    }

    @Override
    public boolean processPayment(PaymentDto paymentDto) {
        return false;
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        String credentials = "igor:igor";
        String base64 = Base64.getEncoder().encodeToString(credentials.getBytes());
        httpHeaders.add("Authorization","Basic "+base64);
        return httpHeaders;
    }
}
