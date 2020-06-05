package com.pluralsight.globomantics.springintegration;

import com.pluralsight.globomantics.springintegration.model.GroupReservation;
import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import com.pluralsight.globomantics.springintegration.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AggregatorExampleApplication implements CommandLineRunner {

	@Autowired
	private ReservationService reservationService;

	@Override
	public void run(String... args) throws Exception {
		GroupReservation groupReservation = new GroupReservation("123");
		groupReservation.getParties().add(new PartyReservation(1, "Double", "Smith"));
		groupReservation.getParties().add(new PartyReservation(2, "Single", "Jones"));
		reservationService.bookGroupReservation(groupReservation);
	}

	public static void main(String[] args) {
		SpringApplication.run(AggregatorExampleApplication.class, args);
	}
}
