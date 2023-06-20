package com.geonwoo.solokill.global.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableRedisHttpSession
public class RedisSessionConfig {

	@Value("${spring.redis.session.host}")
	private String sessionHost;

	@Value("${spring.redis.session.port}")
	private int sessionPort;

	@Primary
	@Bean(name = "redisSession")
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(sessionHost, sessionPort);
	}

	@Bean(name = "sessionTemplate")
	public RedisTemplate<String, Object> redisTemplate() {
		RedisTemplate<String, Object> template = new RedisTemplate<>();
		template.setConnectionFactory(redisConnectionFactory());
		return template;
	}

}
