package com.application.payment.service;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.application.payment.controller.dto.PaymentMode;
import com.application.payment.controller.dto.request.PaymentRequest;
import com.application.payment.controller.dto.response.PaymentResponse;
import com.application.payment.documents.TransactionDetails;
import com.application.payment.repository.TransactionDetailsRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private TransactionDetailsRepository transactionDetailsRepository;

	@Override
	public PaymentResponse doPayment(PaymentRequest paymentRequest) {
		log.info("Recording Payment Details: {}", paymentRequest);
		
		PaymentMode name = paymentRequest.getPaymentMode();
		if ( "PAYPAL".equalsIgnoreCase(name.toString())) {
			
			// implementa pagamento in paypal
			payWithPaypal (paymentRequest);
		}
		

		TransactionDetails transactionDetails = TransactionDetails.builder().paymentDate(Instant.now())
				.paymentMode(paymentRequest.getPaymentMode().name()).paymentStatus("SUCCESS")
				.orderId(paymentRequest.getOrderId()).referenceNumber(paymentRequest.getReferenceNumber())
				.amount(paymentRequest.getAmount()).build();

		transactionDetailsRepository.save(transactionDetails);

		log.info("Transaction Completed with Id: {}", transactionDetails.getTransactionDetailsId());

		String transId = transactionDetails.getTransactionDetailsId();
		return PaymentResponse.builder().paymentId(transId).build();
	}

	@Override
	public PaymentResponse getPaymentDetailsByOrderId(String orderId) {
		log.info("Getting payment details for the Order Id: {}", orderId);

		TransactionDetails transactionDetails = transactionDetailsRepository.findByOrderId(Long.valueOf(orderId));

		return PaymentResponse.builder().paymentId(transactionDetails.getTransactionDetailsId())
				.paymentMode(PaymentMode.valueOf(transactionDetails.getPaymentMode()))
				.paymentDate(transactionDetails.getPaymentDate()).orderId(transactionDetails.getOrderId())
				.status(transactionDetails.getPaymentStatus()).amount(transactionDetails.getAmount()).build();
	}
	
	private void payWithPaypal(PaymentRequest paymentRequest) {
		
		
	}
}