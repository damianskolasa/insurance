package pl.insurance.controller;


import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.log4j.Logger;

import pl.healthinformator.model.HealthCareData;
import pl.insurance.model.Client;
import pl.insurance.service.HealthCareService;
import pl.insurance.service.exception.NoHealthCareDataException;

@Model
public class ClientHealthInformationController {

	@Inject
	private FacesContext facesContext;

	@Inject
	private Logger log;

	@Inject
	private HealthCareService healthCareService;

	@Inject
	@CurrentClient
	private Client currentClient;
	

	private HealthCareData healthCareData = null;
	private Boolean newClient = false;

	@Produces
	@Named
	public HealthCareData getHealthCareData() {
		if (isHealthCareDataLoaded()) {
			try {
				loadHealthCareDataForCurrentUser();
			} catch (NoHealthCareDataException e) {
				createNewHealthCareDataForCurrentClient();
			}
		}
		return healthCareData;
	}

	private boolean isHealthCareDataLoaded() {
		return healthCareData == null;
	}

	private void loadHealthCareDataForCurrentUser() {
		healthCareData = healthCareService
				.clientHealthCareInformations(currentClient);
		log.debug("Health care data loaded for current user...");
	}

	private void createNewHealthCareDataForCurrentClient() {
		log.debug("No stored health care data, creating a new one...");
		healthCareData = new HealthCareData();
		healthCareData.setFirstName(currentClient.getFirstName());
		healthCareData.setLastName(currentClient.getLastName());
		healthCareData.setPesel(currentClient.getPesel());
		newClient = true;
	}

	public String save() {
		try {
			healthCareService.save(healthCareData);
			facesContext.addMessage(null, new FacesMessage(
					"Zaktualizowano pomyślnie"));
		} catch (Exception e) {
			e.printStackTrace();
			facesContext.addMessage(null, new FacesMessage(
					"Błąd aktualizacji danych!!"));
		}
		return null;
	}

	public Boolean isNewClient() {
		return newClient;
	}

	public void showConfirmation() {
		facesContext.addMessage(null, new FacesMessage(
				"Zaktualizowano pomyślnie"));
	}

}
