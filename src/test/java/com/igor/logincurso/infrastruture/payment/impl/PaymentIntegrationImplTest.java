package com.igor.logincurso.infrastruture.payment.impl;

import com.igor.logincurso.dto.payment.CustomerDto;
import com.igor.logincurso.exception.IntegrationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PaymentIntegrationImplTest {
    @Mock
    private RestTemplate restTemplate;
    @InjectMocks
    private PaymentIntegrationImpl paymentIntegration;

    private static HttpHeaders httpHeaders;
    @BeforeAll
    public static void loadHeaders(){
        httpHeaders = getHttpHeaders();
    }
    @Test
    void give_createCustomer_when_apiResponseIs400_then_returnCustomerDto() {
        ReflectionTestUtils.setField(paymentIntegration,"hostUrl","http://localhost:8081");
        ReflectionTestUtils.setField(paymentIntegration,"customerUrl","/v1/customer");
        CustomerDto customerDto = new CustomerDto();
        HttpEntity<CustomerDto> request = new HttpEntity<>(customerDto,httpHeaders);
        Mockito.when(restTemplate.exchange("http://localhost:8081/v1/customer",HttpMethod.POST,
                request, CustomerDto.class)).thenThrow(IntegrationException.class);
        Assertions.assertThrows(IntegrationException.class,
                ()->paymentIntegration.createCustomer(customerDto));
        Mockito.verify(restTemplate,Mockito.times(1))
                .exchange("http://localhost:8081/v1/customer",HttpMethod.POST, request, CustomerDto.class);
    }

    @Test
    void give_createCustomer_when_apiResponseIs200_then_returnIntegrationException() {
        ReflectionTestUtils.setField(paymentIntegration,"hostUrl","http://localhost:8081");
        ReflectionTestUtils.setField(paymentIntegration,"customerUrl","/v1/customer");
        CustomerDto customerDto = new CustomerDto();
        HttpEntity<CustomerDto> request = new HttpEntity<>(customerDto,httpHeaders);
        Mockito.when(restTemplate.exchange("http://localhost:8081/v1/customer",HttpMethod.POST,
                request, CustomerDto.class)).thenReturn(ResponseEntity.of(Optional.of(customerDto)));
        Assertions.assertEquals(customerDto,paymentIntegration.createCustomer(customerDto));
        Mockito.verify(restTemplate,Mockito.times(1))
                .exchange("http://localhost:8081/v1/customer",HttpMethod.POST, request, CustomerDto.class);
    }
    private static HttpHeaders getHttpHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        String credentials = "igor:igor";
        String base64 = Base64.getEncoder().encodeToString(credentials.getBytes());
        httpHeaders.add("Authorization","Basic "+base64);
        return httpHeaders;
    }
}