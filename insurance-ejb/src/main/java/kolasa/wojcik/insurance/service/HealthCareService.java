package kolasa.wojcik.insurance.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import kolasa.wojcik.insurance.model.HealthCareData;

@Stateless
public class HealthCareService {

	@Inject
	private EntityManager em;

	public HealthCareData clientHealthCareInformations(String firstName,
			String lastName, String pesel) {
		return (HealthCareData) em.createQuery(
				"select c.healthRecords from Client c where c.firstName = :firstName and c.lastName = :lastName and c.pesel = :pesel")
				.setParameter("firstName", firstName).setParameter("lastName", lastName).setParameter("pesel", pesel).getSingleResult();
	}
}
