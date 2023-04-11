package com.geonwoo.solokill.global.client.feign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Logger;

@Configuration
@EnableFeignClients("com.geonwoo.solokill")
public class FeignConfig {

	@Bean
	public Logger.Level loggerLevel() {
		return Logger.Level.FULL;
	}
}
