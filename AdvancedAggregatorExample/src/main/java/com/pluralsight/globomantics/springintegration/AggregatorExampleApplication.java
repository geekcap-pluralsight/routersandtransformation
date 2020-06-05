package com.pluralsight.globomantics.springintegration;

import com.pluralsight.globomantics.springintegration.model.GroupReservation;
import com.pluralsight.globomantics.springintegration.model.PartyReservation;
import com.pluralsight.globomantics.springintegration.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class AggregatorExampleApplication implements CommandLineRunner {

	@Autowired
	private ReservationService reservationService;

	@Override
	public void run(String... args) throws Exception {
		GroupReservation groupReservation = new GroupReservation("123");
		groupReservation.getParties().add(new PartyReservation(1, "Double", "Smith"));
		groupReservation.getParties().add(new PartyReservation(2, "Single", "Jones"));
		reservationService.bookGroupReservation(groupReservation, 4);

		GroupReservation groupReservation2 = new GroupReservation("123");
		groupReservation2.getParties().add(new PartyReservation(3, "Double", "Johnson"));
		groupReservation2.getParties().add(new PartyReservation(4, "Single", "Fitzpatrick"));
		reservationService.bookGroupReservation(groupReservation2, 4);
	}

	public static void main(String[] args) {
		SpringApplication.run(AggregatorExampleApplication.class, args);
	}
}
