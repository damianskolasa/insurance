package kolasa.wojcik.insurance.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import kolasa.wojcik.insurance.model.Client;
import kolasa.wojcik.insurance.model.Contract;

@Stateless
public class ContractService {
	
	@Inject
	private EntityManager em;
	
	public List<Contract> clientContracts(Client client) {
	
		return null;
	}
	
	
	public void saveContract(Contract contract) {
		em.persist(contract);
	}
	

}
