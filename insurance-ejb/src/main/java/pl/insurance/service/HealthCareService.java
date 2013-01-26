package pl.insurance.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.insurance.model.Client;
import pl.insurance.model.HealthCareData;
import pl.insurance.service.exception.NoHealthCareDataException;


@Stateless
public class HealthCareService {

	@Inject
	private EntityManager em;

	public HealthCareData clientHealthCareInformations(Client client)
			throws NoHealthCareDataException {

		List<HealthCareData> healthCareDatas = em
				.createQuery(
						"select hcd from HealthCareData hcd where hcd.firstName = :firstName and hcd.lastName = :lastName and hcd.pesel = :pesel", HealthCareData.class)
				.setParameter("firstName", client.getFirstName())
				.setParameter("lastName", client.getLastName())
				.setParameter("pesel", client.getPesel()).getResultList();

		if (healthCareDatas.isEmpty()) {
			throw new NoHealthCareDataException();
		} else {
			return healthCareDatas.get(0);
		}
	}

	public HealthCareData save(HealthCareData healthCareData) {
		healthCareData = em.merge(healthCareData);
		return healthCareData;
	}
}
