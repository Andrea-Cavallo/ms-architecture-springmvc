package com.application.order.mapper;

import java.time.Instant;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.application.order.controller.dto.request.OrderRequest;
import com.application.order.controller.dto.response.OrderResponseDto;
import com.application.order.documents.Order;

@Component
public class OrderMapper {

	public Order toDocument(OrderRequest request) {
		Order document = new Order();
		Instant now = Instant.now();

		request.setOrderDate(now);
		request.setOrderStatus("CREATED");
		BeanUtils.copyProperties(request, document);
		return document;
	}

	public OrderResponseDto toDto(Order doc) {
		OrderResponseDto dto = new OrderResponseDto();
		BeanUtils.copyProperties(doc, dto);
		return dto;
	}

}
