package com.application.order.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.application.order.external.decoder.CustomErrorDecoder;

import feign.codec.ErrorDecoder;

@Configuration
public class FeignConfig {

	@Bean
	ErrorDecoder errorDecoder() {
		return new CustomErrorDecoder();
	}

}
