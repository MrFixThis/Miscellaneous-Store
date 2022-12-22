package com.store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author Bryan Baron
 */
@SpringBootApplication
public class MiscellaneousStoreApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MiscellaneousStoreApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(
			SpringApplicationBuilder application) {
		return application.sources(MiscellaneousStoreApplication.class);
	}
}
