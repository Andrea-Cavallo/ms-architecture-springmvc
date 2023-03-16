package com.application.payment.documents;

import java.time.Instant;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Builder(toBuilder = true)
@Getter
@EqualsAndHashCode
@Document(collection = "transactionDetails")
@NoArgsConstructor
public class TransactionDetails {

	private String transactionDetailsId;

	private Long orderId;

	private String paymentMode;

	private String referenceNumber;

	private Instant paymentDate;

	private String paymentStatus;

	private Double amount;
}