package com.igor.logincurso.domain.service.impl;

import com.igor.logincurso.core.enums.UserTypeEnum;
import com.igor.logincurso.domain.model.jpa.UserCredentials;
import com.igor.logincurso.domain.model.jpa.UserPaymentInfo;
import com.igor.logincurso.domain.model.jpa.UserType;
import com.igor.logincurso.domain.model.jpa.Users;
import com.igor.logincurso.domain.repository.jpa.UserDetailsRepository;
import com.igor.logincurso.domain.repository.jpa.UserPaymentInfoRepository;
import com.igor.logincurso.domain.repository.jpa.UsersRepository;
import com.igor.logincurso.domain.service.PaymentInfoService;
import com.igor.logincurso.domain.service.SubscriptionsTypeService;
import com.igor.logincurso.domain.service.UserTypeService;
import com.igor.logincurso.dto.PaymentProcessDto;
import com.igor.logincurso.dto.payment.CustomerDto;
import com.igor.logincurso.dto.payment.OrderDto;
import com.igor.logincurso.dto.payment.PaymentDto.CreditCardDto;
import com.igor.logincurso.core.event.PagamentoRealizadoEvent;
import com.igor.logincurso.exception.BusinessException;
import com.igor.logincurso.exception.NotFoundException;
import com.igor.logincurso.infrastruture.payment.PaymentIntegration;
import com.igor.logincurso.modelmapper.payment.CreditCardAssembler;
import com.igor.logincurso.modelmapper.UserPaymentAssembler;
import com.igor.logincurso.modelmapper.payment.CustomerAssembler;
import com.igor.logincurso.modelmapper.payment.OrderAssembler;
import com.igor.logincurso.modelmapper.payment.PaymentMapper;
import com.igor.logincurso.utils.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
public class PaymentInfoServiceImpl implements PaymentInfoService {

    @Value("${webservice.igor.password.default}")
    private String defaultPassword;
    @Autowired
    private ApplicationEventPublisher publisher;
    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private SubscriptionsTypeService subscriptionsTypeService;
    @Autowired
    private UserPaymentInfoRepository userPaymentInfoRepository;
    @Autowired
    private UserPaymentAssembler userPaymentAssembler;
    @Autowired
    private UserTypeService userTypeService;
    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private PaymentIntegration paymentIntegration;
    @Override
    @Transactional
    public Boolean process(PaymentProcessDto dto) {
        //verifica se exixte usuario por id e se ja tem assinatura
        Users user = getUsers(dto);

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
            userPaymentInfo.setUsers(user);
            userPaymentInfoRepository.save(userPaymentInfo);

            //verifica se o produto existe e associa ao usuário
            var subscriptionsType = subscriptionsTypeService.findByProductKey(dto.getProductKey());
            user.setSubscriptionsType(subscriptionsType);

            //salva o userCredentials
            UserType userType = userTypeService.findById(UserTypeEnum.ALUNO.getId());
            UserCredentials userCredentials = new UserCredentials(null,user.getEmail(), PasswordUtils.encode(defaultPassword), userType);
            userDetailsRepository.save(userCredentials);

            //criar evento de pagamento confirmado para envio de email
            publisher.publishEvent(new PagamentoRealizadoEvent(this,"Igor Curso",userPaymentInfo));
            return true;
        }
        return false;
    }

    private Users getUsers(PaymentProcessDto dto) {
        Users user = usersRepository.findById(dto.getUserPaymentDto().getUserId()).orElseThrow(
                ()->new NotFoundException("Usuário não encontrado")
        );
        if (Objects.nonNull(user.getSubscriptionsType())){
            throw new BusinessException("Pagamento não pode ser processado pois usuário já possui assinatura");
        }
        return user;
    }
}
