package com.igor.logincurso.core.paypal;

import com.paypal.base.rest.APIContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PayPalConfig {
    @Value("${payment.api.clientId}")
    private String clientId;

    @Value("${payment.api.clientSecret}")
    private String clientSecret;

    @Bean
    public APIContext apiContext() {
        return new APIContext(clientId, clientSecret, "sandbox"); // Use "sandbox" para ambiente de desenvolvimento
    }
}
