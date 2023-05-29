package com.geonwoo.solokill.global.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

@EnableCaching
@Configuration
public class CacheConfig {
	//
	// @Bean
	// public CacheManager cacheManager() {
	// 	return new ConcurrentMapCacheManager("matchRecordCacheStore");
	// }
}
