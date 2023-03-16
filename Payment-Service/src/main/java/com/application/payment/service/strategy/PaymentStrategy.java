package com.application.payment.service.strategy;

import com.application.payment.controller.dto.Currency;
import com.application.payment.controller.dto.PaymentMode;

public interface PaymentStrategy {
    boolean processPayment(double amount, Currency currency );
}