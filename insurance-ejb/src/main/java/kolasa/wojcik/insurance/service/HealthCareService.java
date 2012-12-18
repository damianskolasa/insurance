package kolasa.wojcik.insurance.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import kolasa.wojcik.insurance.model.Client;
import kolasa.wojcik.insurance.model.HealthCareData;
import kolasa.wojcik.insurance.service.exception.NoHealthCareDataException;

@Stateless
public class HealthCareService {

	@Inject
	private EntityManager em;

	@SuppressWarnings("unchecked")
	public HealthCareData clientHealthCareInformations(Client client)
			throws NoHealthCareDataException {

		List<HealthCareData> healthCareDatas = em
				.createQuery(
						"select hcd from HealthCareData hcd where hcd.firstName = :firstName and hcd.lastName = :lastName and hcd.pesel = :pesel")
				.setParameter("firstName", client.getFirstName())
				.setParameter("lastName", client.getLastName())
				.setParameter("pesel", client.getPesel()).getResultList();

		if (healthCareDatas.isEmpty()) {
			throw new NoHealthCareDataException();
		} else {
			return healthCareDatas.get(0);
		}
	}

	public void save(HealthCareData healthCareData) {
		em.merge(healthCareData);
		em.flush();
	}
}
