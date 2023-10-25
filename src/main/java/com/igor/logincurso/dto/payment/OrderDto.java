package com.igor.logincurso.dto.payment;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String id;
    private String customerId;
    private BigDecimal discount;
    private String productAcronym;
}
