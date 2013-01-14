package pl.insurance.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;

import pl.addresser.model.Street;
import pl.addresser.rest.StreetRESTService;
import pl.insurance.model.Address;

@Stateless
public class AddressService {

	@Inject
	private EntityManager em;

	public Address findAddressById(Long addressId) {
		Address address = em
				.createQuery("Select a from Address a where a.id = :id",
						Address.class).setParameter("id", addressId)
				.getSingleResult();

		address.setStreet(getStreetRESTService().lookupById(
				address.getStreetId()));
		return address;
	}

	public Address addAddress(Address address) {
		address.setStreetId(address.getStreet().getId());
		return em.merge(address);
	}

	public List<Street> suggestStreets(String pattern) {
		return getStreetRESTService().suggestStreets(pattern);
	}

	public Street findStreetByDescription(String description) {

		return getStreetRESTService().lookupByDescription(description);
	}

	public StreetRESTService getStreetRESTService() {
		return JAXRSClientFactory
				.create("http://localhost:8081/addresser/rest",
						StreetRESTService.class);
	}
}
