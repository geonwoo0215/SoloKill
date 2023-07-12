package com.geonwoo.solokill.global.config;

// @Configuration
// @EnableRedisHttpSession
// @Profile("!test")
// public class RedisSessionConfig {
//
// 	@Value("${spring.redis.session.host}")
// 	private String sessionHost;
//
// 	@Value("${spring.redis.session.port}")
// 	private int sessionPort;
//
// 	@Primary
// 	@Bean(name = "redisSession")
// 	public RedisConnectionFactory redisConnectionFactory() {
// 		return new LettuceConnectionFactory(sessionHost, sessionPort);
// 	}
//
// 	@Bean(name = "sessionTemplate")
// 	public RedisTemplate<String, Object> redisTemplate() {
// 		RedisTemplate<String, Object> template = new RedisTemplate<>();
// 		template.setConnectionFactory(redisConnectionFactory());
// 		template.setKeySerializer(new StringRedisSerializer());
// 		template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
// 		return template;
// 	}
//
// }
