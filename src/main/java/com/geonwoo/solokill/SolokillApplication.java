package com.geonwoo.solokill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class SolokillApplication {

	public static void main(String[] args) {
		SpringApplication.run(SolokillApplication.class, args);
	}

}
