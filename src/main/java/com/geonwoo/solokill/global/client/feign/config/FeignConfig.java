package com.geonwoo.solokill.global.client.feign.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.geonwoo.solokill.global.properties.ApiKeyProperties;

import feign.Logger;
import feign.RequestInterceptor;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableFeignClients("com.geonwoo.solokill")
@RequiredArgsConstructor
public class FeignConfig {

	public final ApiKeyProperties apiKeyProperties;

	@Bean
	public RequestInterceptor requestInterceptor() {
		return requestTemplate -> requestTemplate.header("X-Riot-Token", apiKeyProperties.key());
	}

	@Bean
	public Logger.Level loggerLevel() {
		return Logger.Level.FULL;
	}
}
