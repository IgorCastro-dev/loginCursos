package com.igor.logincurso.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private CreditCardDto creditCard;
    private String customerId;
    private String orderId;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreditCardDto{
        private Long cvv;
        private String documentNumber;
        private Long installments;
        private Long month;
        private String number;
        private Long year;
    }
}
