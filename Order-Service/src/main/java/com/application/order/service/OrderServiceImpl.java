package com.application.order.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.application.order.controller.dto.request.OrderRequest;
import com.application.order.controller.dto.response.OrderResponseDto;
import com.application.order.controller.dto.response.OrderResponseDto.ProductDetails;
import com.application.order.controller.exception.OrderNotFoundException;
import com.application.order.documents.Order;
import com.application.order.external.client.PaymentService;
import com.application.order.external.client.ProductService;
import com.application.order.external.request.PaymentRequest;
import com.application.order.external.response.ProductDto;
import com.application.order.mapper.OrderMapper;
import com.application.order.repo.OrderRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderMapper orderMapper;

	private OrderRepository orderRepository;
	@Autowired
	private ProductService productService;
	@Autowired
	private PaymentService paymentService;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, timeout = 60)
	public OrderResponseDto createOrder(OrderRequest orderRequest) {
		log.info("con il feign client uso l'altro ms, quindi prima dell'ordine riduco la quantita'");
		productService.reduceQuantity(orderRequest.getProductId(), orderRequest.getQuantity());
		log.info("poi faccio il place dell'order");
		Order order = orderMapper.toDocument(orderRequest);

		log.info("Calling the payment Service");
		PaymentRequest paymentReq = PaymentRequest.builder().amount(orderRequest.getTotalAmount())
				.paymentMode(orderRequest.getPaymentMode()).build();

		try {
			paymentService.pay(paymentReq);
			order.setOrderStatus("ORDER_PLACED");
		} catch (Exception e) {
			log.error("Error during payment ");
			order.setOrderStatus("ORDER_FAILED");

		}
		Order saved = orderRepository.save(order);

		return orderMapper.toDto(saved);
	}

	@Override
	public OrderResponseDto getOrder(OrderRequest orderRequest) {
		String productId = orderRequest.getProductId();
		Order order = Order.builder().productId(productId).build();
		Optional<Order> optionalOrder = orderRepository.findOne(Example.of(order));

		if (optionalOrder.isEmpty()) {
			throw new OrderNotFoundException(productId);
		}

		OrderResponseDto orderResponseDto = orderMapper.toDto(optionalOrder.get());
		ProductDto productDto = restTemplate.getForObject("http://PRODUCT-SERVICE/products/" + productId,
				ProductDto.class);
		ProductDetails productDetails = ProductDetails.builder()
				.price(productDto != null ? productDto.getPrice() : null)
				.productId(productDto != null ? productDto.getProductId() : null)
				.productName(productDto != null ? productDto.getProductName() : null)
				.quantity(productDto != null ? productDto.getQuantity() : null).build();
		orderResponseDto.setProductDetails(productDetails);

		return orderResponseDto;
	}

}
