package com.igor.logincurso.infrastruture.payment;

import com.paypal.api.payments.Payment;
import org.springframework.stereotype.Service;

@Service
public class PaypalService implements PaymentService{
    @Override
    public void pagar() {
        Payment createdPayment = new Payment()
                .

//                .intent("sale")
//                .payer(new Payer().paymentMethod("paypal"))
//                .transactions(Collections.singletonList(
//                        new Transaction()
//                                .amount(new Amount().total("10.00").currency("USD"))
//                ))
//                .redirectUrls(new RedirectUrls().returnUrl("http://example.com/return").cancelUrl("http://example.com/cancel"));

        createdPayment.create(apiContext);

    }
}
