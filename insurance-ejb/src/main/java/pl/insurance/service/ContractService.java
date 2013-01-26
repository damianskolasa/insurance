package pl.insurance.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import pl.insurance.model.Client;
import pl.insurance.model.Contract;


@Stateless
public class ContractService {

	@Inject
	private EntityManager em;

	public List<Contract> clientContracts(Client client) {
		return em
				.createQuery(
						"select c from Contract c where c.client = :client",
						Contract.class).setParameter("client", client)
				.getResultList();
	}

	public void saveContract(Contract contract) {
		em.persist(contract);
	}

}
