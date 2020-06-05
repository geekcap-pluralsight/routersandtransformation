package com.pluralsight.globomantics.springintegration;

import com.pluralsight.globomantics.springintegration.model.PurchaseOrder;
import com.pluralsight.globomantics.springintegration.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GenericRouterExampleApplication implements CommandLineRunner {

	@Autowired
	private PaymentService paymentService;

	@Override
	public void run(String... args) throws Exception {
		paymentService.submitPurchaseOrder(new PurchaseOrder("Customer 1", 500f));
		paymentService.submitPurchaseOrder(new PurchaseOrder("Customer 2", 50000f));
	}

	public static void main(String[] args) {
		SpringApplication.run(GenericRouterExampleApplication.class, args);
	}
}
