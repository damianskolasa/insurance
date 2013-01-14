package pl.insurance.service;

import java.util.List;

import javax.ejb.Stateless;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import pl.healthinformator.model.HealthCareData;
import pl.healthinformator.rest.HealthInformationRESTService;
import pl.insurance.model.Client;
import pl.insurance.service.exception.NoHealthCareDataException;

@Stateless
public class HealthCareService {

	public HealthCareData clientHealthCareInformations(Client client)
			throws NoHealthCareDataException {

		List<HealthCareData> healthCareDatas = getHealthInformationRESTService().lookupByInfo(client.getFirstName(), client.getLastName(), client.getPesel());

		if (healthCareDatas.isEmpty()) {
			throw new NoHealthCareDataException();
		} else {
			return healthCareDatas.get(0);
		}
	}

	public HealthCareData save(HealthCareData healthCareData) {
		healthCareData = getHealthInformationRESTService().update(healthCareData);
		return healthCareData;
	}
	
	public HealthInformationRESTService getHealthInformationRESTService() {
		return JAXRSClientFactory
				.create("http://localhost:8081/healthinformator/rest",
						HealthInformationRESTService.class);
	}
}
