package com.geonwoo.solokill.global.firebase;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FCMInitializer {

	@Value("${fcm.certification}")
	private String googleApplicationCredentials;

	@PostConstruct
	public void initialize() {

		ClassPathResource resource = new ClassPathResource(googleApplicationCredentials);
		try (InputStream is = resource.getInputStream()) {
			FirebaseOptions options = FirebaseOptions.builder()
				.setCredentials(GoogleCredentials.fromStream(is))
				.build();

			if (FirebaseApp.getApps().isEmpty()) {
				FirebaseApp.initializeApp(options);
				log.info("FirebaseApp initialization complete");
			}
		} catch (IOException e) {
			log.error(e.getMessage());
			throw new RuntimeException(e.getMessage());
		}
	}

}
