package com.geonwoo.solokill.global.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.geonwoo.solokill.global.security.AuthenticationInterceptor;
import com.geonwoo.solokill.global.security.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LoginInterceptor())
			.addPathPatterns("")
			.order(1);
		registry.addInterceptor(new AuthenticationInterceptor())
			.addPathPatterns("")
			.order(2);
	}


}
