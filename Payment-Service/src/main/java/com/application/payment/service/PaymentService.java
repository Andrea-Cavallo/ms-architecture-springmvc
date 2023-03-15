package com.application.payment.service;

import com.application.payment.controller.dto.request.PaymentRequest;
import com.application.payment.controller.dto.response.PaymentResponse;

public interface PaymentService {
	PaymentResponse doPayment(PaymentRequest paymentRequest);

	PaymentResponse getPaymentDetailsByOrderId(String orderId);
}