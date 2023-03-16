package com.application.payment.service.strategy;
import com.application.payment.controller.dto.Currency;
import com.application.payment.controller.dto.PaymentMode;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.param.ChargeCreateParams;

public class MasterCardPaymentStrategy implements PaymentStrategy {
    private static final String API_KEY = "your-api-key";

    @Override
    public boolean processPayment(double amount, Currency currency ) {
        Stripe.apiKey = API_KEY;

        ChargeCreateParams params = ChargeCreateParams.builder()
            .setAmount((long) (amount * 100))
            .setCurrency(currency.toString())
            .setDescription(PaymentMode.MASTERCARD.toString())
            .setSource("tok_visa") // Replace with the actual card token obtained from Stripe.js or Stripe Elements
            .build();

        try {
            Charge charge = Charge.create(params);
            if ("succeeded".equals(charge.getStatus())) {
                System.out.println("Card payment succeeded: " + charge.getId());
                return true;
            } else {
                System.err.println("Card payment failed: " + charge.getFailureMessage());
                return false;
            }
        } catch (StripeException e) {
            System.err.println("Error processing card payment: " + e.getMessage());
            return false;
        }
    }
}

