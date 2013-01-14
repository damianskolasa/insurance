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
	private ProductService productService;

	@Inject
	private EntityManager em;

	public List<Contract> clientContracts(Client client) throws Exception {
		List<Contract> clientContracts = em
				.createQuery(
						"select c from Contract c where c.client = :client",
						Contract.class).setParameter("client", client)
				.getResultList();

		for (Contract contract : clientContracts) {
			contract.setProduct(productService.findProductById(contract
					.getProductId()));
		}

		return clientContracts;
	}

	public void saveContract(Contract contract) {
		contract.setProductId(contract.getProduct().getId());
		em.persist(contract);
	}

}
