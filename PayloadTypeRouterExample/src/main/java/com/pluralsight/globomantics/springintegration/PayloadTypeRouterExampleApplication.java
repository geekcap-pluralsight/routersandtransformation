package com.pluralsight.globomantics.springintegration;

import com.pluralsight.globomantics.springintegration.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PayloadTypeRouterExampleApplication implements CommandLineRunner {

	@Autowired
	private ReservationService registrationService;

	@Override
	public void run(String... args) throws Exception {
		registrationService.addReservationRecord("1", "John Smith");
		registrationService.addReservationRecord("2", "Jane Smith");
		registrationService.completeReservation("1", "123");
	}

	public static void main(String[] args) {
		SpringApplication.run(PayloadTypeRouterExampleApplication.class, args);
	}
}
