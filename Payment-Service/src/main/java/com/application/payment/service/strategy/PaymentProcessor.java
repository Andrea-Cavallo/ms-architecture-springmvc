package com.application.payment.service.strategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.application.payment.controller.dto.Currency;
import com.application.payment.controller.dto.PaymentMode;

import java.util.HashMap;
import java.util.Map;

@Component
public class PaymentProcessor {
    private Map<PaymentMode, PaymentStrategy> paymentStrategies;

    @Autowired
    public PaymentProcessor(@Qualifier("visaPaymentStrategy") PaymentStrategy visaPaymentStrategy,
                            @Qualifier("masterCardPaymentStrategy") PaymentStrategy masterCardPaymentStrategy,
                            @Qualifier("bitcoinPaymentStrategy") PaymentStrategy bitcoinPaymentStrategy) {
        this.paymentStrategies = new HashMap<>();
        this.paymentStrategies.put(PaymentMode.VISA, visaPaymentStrategy);
        this.paymentStrategies.put(PaymentMode.MASTERCARD, masterCardPaymentStrategy);
        this.paymentStrategies.put(PaymentMode.BITCOIN, bitcoinPaymentStrategy);
    }

    public boolean executePayment(PaymentMode paymentMode, double amount, Currency currency) {
        PaymentStrategy paymentStrategy = this.paymentStrategies.get(paymentMode);
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set");
        }
        return paymentStrategy.processPayment(amount, currency);
    }
}
