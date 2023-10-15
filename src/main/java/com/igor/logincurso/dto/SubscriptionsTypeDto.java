package com.igor.logincurso.dto;

import lombok.Data;

import javax.persistence.Column;
import java.math.BigDecimal;

@Data
public class SubscriptionsTypeDto {
    private String name;
    private Long accessMonths;
    private BigDecimal price;
    private String productKey;
}
