package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.domain.model.UserPaymentInfo;
import com.igor.logincurso.domain.model.Users;
import com.igor.logincurso.domain.repository.UserPaymentInfoRepository;
import com.igor.logincurso.domain.repository.UsersRepository;
import com.igor.logincurso.domain.service.PaymentInfoService;
import com.igor.logincurso.dto.PaymentProcessDto;
import com.igor.logincurso.dto.payment.CustomerDto;
import com.igor.logincurso.dto.payment.OrderDto;
import com.igor.logincurso.dto.payment.PaymentDto.CreditCardDto;
import com.igor.logincurso.exception.BusinessException;
import com.igor.logincurso.exception.NotFoundException;
import com.igor.logincurso.infrastruture.payment.PaymentIntegration;
import com.igor.logincurso.modelmapper.payment.CreditCardAssembler;
import com.igor.logincurso.modelmapper.UserPaymentAssembler;
import com.igor.logincurso.modelmapper.payment.CustomerAssembler;
import com.igor.logincurso.modelmapper.payment.OrderAssembler;
import com.igor.logincurso.modelmapper.payment.PaymentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {
    @Autowired
    private UsersRepository usersRepository;
    @Autowired
    private UserPaymentInfoRepository userPaymentInfoRepository;
    @Autowired
    private UserPaymentAssembler userPaymentAssembler;
    @Autowired
    private PaymentIntegration paymentIntegration;
    @Override
    @Transactional
    public Boolean process(PaymentProcessDto dto) {
        //verifica se exixte usuario por id e se ja tem assinatura
        Users user = usersRepository.findById(dto.getUserPaymentDto().getId()).orElseThrow(
                ()->new NotFoundException("Usuário não encontrado")
        );
        if (Objects.nonNull(user.getSubscriptionsType())){
            throw new BusinessException("Pagamento não pode ser processado pois usuário já possui assinatura");
        }

        //cria ou atualiza o customer na api payment
        CustomerDto customerDto = paymentIntegration.createCustomer(CustomerAssembler.build(user));

        //cria o order na api payment
        OrderDto orderDto = paymentIntegration.createOrder(OrderAssembler.build(customerDto,dto));

        //processa o pagamento
        CreditCardDto creditCardDto = CreditCardAssembler.build(dto.getUserPaymentDto(),customerDto);
        Boolean successPayment = paymentIntegration.processPayment(PaymentMapper.build(creditCardDto,customerDto,orderDto));
        if (Boolean.TRUE.equals(successPayment)){
            //salva o user payment
            UserPaymentInfo userPaymentInfo = userPaymentAssembler.dtoToEntity(dto.getUserPaymentDto());
            userPaymentInfoRepository.save(userPaymentInfo);
        }
        //enviar o email de confirmação
        return true;
    }
}
