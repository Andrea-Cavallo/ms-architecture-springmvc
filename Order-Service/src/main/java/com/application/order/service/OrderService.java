package com.application.order.service;

import com.application.order.controller.dto.request.OrderRequest;
import com.application.order.controller.dto.response.OrderResponseDto;

public interface OrderService {

	OrderResponseDto createOrder(OrderRequest orderRequest);

	OrderResponseDto getOrder(OrderRequest orderRequest);

}
