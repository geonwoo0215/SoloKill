package com.geonwoo.solokill.global.config;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Configuration
@EnableCaching
public class RedisConfig {

	@Value("${spring.redis.cache.host}")
	private String host;

	@Value("${spring.redis.cache.port}")
	private int port;

	@Bean(name = "redisCache")
	public RedisConnectionFactory redisConnectionFactory() {
		return new LettuceConnectionFactory(host, port);
	}

	private ObjectMapper objectMapper() {
		ObjectMapper mapper = new ObjectMapper();
		mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
		mapper.registerModule(new JavaTimeModule());
		return mapper;
	}

	private RedisCacheConfiguration redisCacheConfiguration() {
		return RedisCacheConfiguration
			.defaultCacheConfig()
			.entryTtl(Duration.ofSeconds(3600))
			.serializeKeysWith(RedisSerializationContext.SerializationPair
				.fromSerializer(new StringRedisSerializer()))
			.serializeValuesWith(RedisSerializationContext.SerializationPair
				.fromSerializer(new GenericJackson2JsonRedisSerializer(objectMapper())));
	}

	@Bean
	public CacheManager redisCacheManager() {
		return RedisCacheManager.RedisCacheManagerBuilder
			.fromConnectionFactory(redisConnectionFactory())
			.cacheDefaults(redisCacheConfiguration())
			.build();
	}
}
