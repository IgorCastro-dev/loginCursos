package com.igor.logincurso.domain.controller;

import com.igor.logincurso.domain.service.PaymentInfoService;
import com.igor.logincurso.dto.PaymentProcessDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/payment")
public class PaymentInfoController {

    @Autowired
    private PaymentInfoService paymentInfoService;

    @PostMapping("/process")
    public ResponseEntity<Boolean> processPayment(@RequestBody @Valid PaymentProcessDto dto){
        return ResponseEntity.ok(paymentInfoService.process(dto));

    }
}
