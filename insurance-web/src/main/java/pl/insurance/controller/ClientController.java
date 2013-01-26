package pl.insurance.controller;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;

import pl.insurance.model.Client;



public class ClientController {

	@Produces
	@SessionScoped
	@CurrentClient
	public Client getCurrentClient() {
		Client client = new Client();
		client.setFirstName("Test");
		client.setLastName("Testowy");
		client.setPesel("123456");
		return client;
	}
	
}
