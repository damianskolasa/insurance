package kolasa.wojcik.insurance.service;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import kolasa.wojcik.insurance.model.Address;

@Stateless
public class AddressService {

	@Inject
	private EntityManager em;
	
	public Address findAddressById(Long addressId) {
		
		return null;
	}
	
	public void addAddress(Address address) {
		em.persist(address);
	}
}
