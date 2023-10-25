package com.igor.logincurso.domain.service;

import com.igor.logincurso.dto.PaymentProcessDto;

public interface PaymentInfoService {
    Boolean process(PaymentProcessDto dto);
}
