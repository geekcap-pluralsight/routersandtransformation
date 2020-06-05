package com.pluralsight.globomantics.springintegration;

import com.pluralsight.globomantics.springintegration.model.FamilyReservation;
import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import com.pluralsight.globomantics.springintegration.service.FamilyReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TransformerExampleApplication implements CommandLineRunner {

	@Autowired
	private FamilyReservationService familyReservationService;

	@Override
	public void run(String... args) throws Exception {
		FamilyReservation familyReservation = new FamilyReservation(
				1, "Smith", "Deluxe", 2);
		familyReservationService.bookFamilyReservation(familyReservation);
	}

	public static void main(String[] args) {
		SpringApplication.run(TransformerExampleApplication.class, args);
	}
}
