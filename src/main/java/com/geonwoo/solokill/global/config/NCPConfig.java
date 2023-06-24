package com.geonwoo.solokill.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.geonwoo.solokill.global.properties.NCPProperties;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class NCPConfig {

	private final NCPProperties ncpProperties;

	@Bean
	public AmazonS3 amazonS3() {
		return AmazonS3ClientBuilder.standard()
			.withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(ncpProperties.objectEndpoint(),
				ncpProperties.regionName()))
			.withCredentials(new AWSStaticCredentialsProvider(
				new BasicAWSCredentials(ncpProperties.accessKey(), ncpProperties.secretKey())))
			.build();
	}

}
