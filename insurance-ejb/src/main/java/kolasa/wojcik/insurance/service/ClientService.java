package kolasa.wojcik.insurance.service;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import kolasa.wojcik.insurance.model.Client;

@Stateless
public class ClientService {

	@Inject
	private EntityManager em;

	public void registerClient(Client client) {
		em.persist(client);
	}

	public List<Client> listClients() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Client> criteria = cb.createQuery(Client.class);
		Root<Client> client = criteria.from(Client.class);

		criteria.select(client).orderBy(cb.asc(client.get("name")));
		return em.createQuery(criteria).getResultList();
	}

	public Client findClientById(Long clientId) {
		return (Client) em.createQuery("Select c from Client c where c.id = :id")
				.setParameter("id", clientId).getSingleResult();
	}

	public Client findClientByPESEL(String pesel) {
		return (Client) em
				.createQuery("Select c from Client c where c.pesel = :pesel")
				.setParameter("pesel", pesel).getSingleResult();
	}

}
