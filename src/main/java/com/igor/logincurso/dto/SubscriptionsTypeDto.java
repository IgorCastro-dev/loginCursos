package com.igor.logincurso.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

@Data
public class SubscriptionsTypeDto {

    @NotBlank
    private String name;

    @Max(value = 12)
    private Long accessMonths;

    @NotNull
    private BigDecimal price;

    @NotBlank
    @Size(min = 5,max = 15)
    private String productKey;
}
