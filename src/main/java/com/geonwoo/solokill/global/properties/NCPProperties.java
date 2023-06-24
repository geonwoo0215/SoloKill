package com.geonwoo.solokill.global.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ncp")
public record NCPProperties(
	String accessKey,
	String secretKey,
	String regionName,
	String objectEndpoint,
	String bucketName,
	String folderName
) {

}
