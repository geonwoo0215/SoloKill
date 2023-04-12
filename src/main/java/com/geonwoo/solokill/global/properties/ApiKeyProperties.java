package com.geonwoo.solokill.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "riot-api")
public record ApiKeyProperties(
	String key
) {
}
