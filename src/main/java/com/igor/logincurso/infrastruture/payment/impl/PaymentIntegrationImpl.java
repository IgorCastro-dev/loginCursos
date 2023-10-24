package com.igor.logincurso.infrastruture.payment.impl;

import com.igor.logincurso.dto.payment.CustomerDto;
import com.igor.logincurso.dto.payment.OrderDto;
import com.igor.logincurso.dto.payment.PaymentDto;
import com.igor.logincurso.exception.IntegrationException;
import com.igor.logincurso.infrastruture.payment.PaymentIntegration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.util.Base64;

@Component
public class PaymentIntegrationImpl implements PaymentIntegration {

    @Value("${webservices.payment.host}")
    private String hostUrl;

    @Value("${webservices.payment.v1.customer}")
    private String customerUrl;

    @Value("${webservices.payment.v1.order}")
    private String orderUrl;

    @Value("${webservices.payment.v1.payment}")
    private String paymentUrl;
    private final RestTemplate restTemplate;
    private final HttpHeaders httpHeaders;
    public PaymentIntegrationImpl(){
        this.restTemplate = new RestTemplate();
        this.httpHeaders = getHttpHeaders();
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {
        try {
            HttpEntity<CustomerDto> request = new HttpEntity<>(customerDto,httpHeaders);
            ResponseEntity<CustomerDto> response = restTemplate.exchange(
                    hostUrl+customerUrl, HttpMethod.POST,
                    request,CustomerDto.class);
            return response.getBody();
        }catch (Exception e){
            throw new IntegrationException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {
        try {
            HttpEntity<OrderDto> request = new HttpEntity<>(orderDto,httpHeaders);
            ResponseEntity<OrderDto> response = restTemplate.exchange(
                    hostUrl+orderUrl,HttpMethod.POST,
                    request,OrderDto.class);
            return response.getBody();
        }catch (Exception e){
            throw new IntegrationException(e.getMessage(),e.getCause());
        }
    }

    @Override
    public Boolean processPayment(PaymentDto paymentDto) {
        try {
            HttpEntity<PaymentDto> request = new HttpEntity<>(paymentDto,httpHeaders);
            ResponseEntity<Boolean> response = restTemplate.exchange(
                    hostUrl+paymentUrl,HttpMethod.POST,
                    request,Boolean.class);
            return response.getBody();
        }catch (Exception e){
            throw new IntegrationException(e.getMessage(),e.getCause());
        }
    }

    private static HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        String credentials = "igor:igor";
        String base64 = Base64.getEncoder().encodeToString(credentials.getBytes());
        httpHeaders.add("Authorization","Basic "+base64);
        return httpHeaders;
    }
}
