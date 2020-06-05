package com.pluralsight.globomantics.springintegration;

import com.pluralsight.globomantics.springintegration.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RecipientListRouterExampleApplication implements CommandLineRunner {

	@Autowired
	private LocationService locationService;

	@Override
	public void run(String... args) throws Exception {
		locationService.saveLocation("123", "Customer 1");
		locationService.saveLocation("456", "Customer 2");
	}

	public static void main(String[] args) {
		SpringApplication.run(RecipientListRouterExampleApplication.class, args);
	}
}
